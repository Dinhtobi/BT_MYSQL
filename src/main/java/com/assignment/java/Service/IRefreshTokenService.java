package com.assignment.java.Service;

import com.assignment.java.DTO.Payload.Response.RefreshTokenResponse;
import com.assignment.java.Entities.RefreshToken;

public interface IRefreshTokenService {
	String createOrUpdateRefreshToken(int user_id);
	RefreshTokenResponse getRefreshTokenByToken(String token);
	
	boolean verifyExpiration(RefreshToken token);
	
	void DeleteTokenByUser(int id);
}
