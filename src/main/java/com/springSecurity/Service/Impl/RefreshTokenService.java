package com.springSecurity.Service.Impl;

import java.time.Instant;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nimbusds.oauth2.sdk.TokenResponse;
import com.springSecurity.Entities.RefreshToken;
import com.springSecurity.Entities.User;
import com.springSecurity.Exception.NotFoundException;
import com.springSecurity.Exception.TokenRefreshException;
import com.springSecurity.Payload.Response.TokenRefreshResponse;
import com.springSecurity.Repository.RefreshTokenRepository;
import com.springSecurity.Repository.UserRepository;
import com.springSecurity.Service.IRefreshTokenService;
import com.springSecurity.utils.JwtUtil;

@Service
public class RefreshTokenService implements IRefreshTokenService {

	private int refreshTokenDurationMs =3*60*1000;
	
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RefreshTokenRepository refreshTokenRepository;
	
	@Autowired
	private JwtUtil jwtUtil;
	@Override
	public String createRefreshToken(int user_id) {
		RefreshToken refreshToken = new RefreshToken();
		refreshToken.setId(user_id);
		User user = userRepository.findById(user_id).orElseThrow(() -> new NotFoundException("User not found"));
		refreshToken.setUser(user);
		String token = UUID.randomUUID().toString();
		refreshToken.setToken(token);
		refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
		refreshTokenRepository.save(refreshToken);
		return token;
	}

	@Override
	public TokenRefreshResponse getRefreshTokenByToken(String token) {
		RefreshToken refreshToken = refreshTokenRepository.findByToken(token).orElseThrow(()-> new NotFoundException("Token Invalid"));
		if(verifyExpiration(refreshToken)) {
			TokenRefreshResponse resp = new TokenRefreshResponse();
			resp.setAccessToken(jwtUtil.generateToken(refreshToken.getUser().getUsername()));
			resp.setRefreshToken(refreshToken.getToken());
			return resp;
		}
		else return null ;
	}

	@Override
	public boolean verifyExpiration(RefreshToken token) {
		if(token.getExpiryDate().compareTo(Instant.now()) < 0) {
			throw new TokenRefreshException("Token is Expirated");
		}
		return true;
	}

	@Override
	public void DeleteTokenByUser(int id) {
		refreshTokenRepository.deleteById(id);
	}

}
