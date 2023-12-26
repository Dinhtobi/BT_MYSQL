package com.assignment.java.Service;

import com.assignment.java.Entities.RefreshToken;
import com.assignment.java.Payload.Response.RefreshTokenResponse;

public interface IRefreshTokenService {
	String createOrUpdateRefreshToken(int user_id);
	RefreshTokenResponse getRefreshTokenByToken(String token);
	
	boolean verifyExpiration(RefreshToken token);
	
	void DeleteTokenByUser(int id);
}
