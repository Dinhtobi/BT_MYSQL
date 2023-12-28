package com.assignment.java.Service.Impl;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.java.DTO.Payload.Response.RefreshTokenResponse;
import com.assignment.java.Entities.RefreshToken;
import com.assignment.java.Entities.User;
import com.assignment.java.Exception.NotFoundException;
import com.assignment.java.Exception.RefreshTokenException;
import com.assignment.java.Repository.RefreshTokenRepository;
import com.assignment.java.Repository.UserRepository;
import com.assignment.java.Service.IRefreshTokenService;
import com.assignment.java.Utils.JwtUtil;

@Service
public class RefreshTokenService implements IRefreshTokenService {

	private int refreshTokenDurationMs = 8*60*60*1000;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RefreshTokenRepository refreshTokenRepository;

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	public String createOrUpdateRefreshToken(int user_id) {
		
		Optional<Integer> refreshTokenOptional = refreshTokenRepository.findByUserId(user_id);
		String token = UUID.randomUUID().toString();
		if(refreshTokenOptional.isPresent()) {
			refreshTokenRepository.updateToken(token, Instant.now().plusMillis(refreshTokenDurationMs), user_id);
		}else {
			RefreshToken refreshToken= new RefreshToken();
			refreshToken.setId(user_id);
			User user = userRepository.findById(user_id).orElseThrow(() -> new NotFoundException("User not found"));
			refreshToken.setUser(user);
			refreshToken.setToken(token);
			refreshToken.setExpiration(Instant.now().plusMillis(refreshTokenDurationMs));
			refreshTokenRepository.save(refreshToken);
		}
		return token;
	}

	@Override
	public RefreshTokenResponse getRefreshTokenByToken(String token) {
		RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
				.orElseThrow(() -> new NotFoundException("Token Invalid"));
		if (verifyExpiration(refreshToken)) {
			RefreshTokenResponse resp = new RefreshTokenResponse();
			resp.setAccessToken(jwtUtil.generateToken(refreshToken.getUser().getEmail()));
			resp.setRefreshToken(refreshToken.getToken());
			return resp;
		} else
			return null;
	}

	@Override
	public boolean verifyExpiration(RefreshToken token) {
		if (token.getExpiration().compareTo(Instant.now()) < 0) {
			throw new RefreshTokenException("Token is Expirated");
		}
		return true;
	}

	@Override
	public void DeleteTokenByUser(int id) {
		refreshTokenRepository.deleteById(id);
	}

}
