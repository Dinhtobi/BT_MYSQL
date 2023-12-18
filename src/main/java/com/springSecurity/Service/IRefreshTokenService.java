package com.springSecurity.Service;

import com.springSecurity.Entities.RefreshToken;
import com.springSecurity.Payload.Response.TokenRefreshResponse;

public interface IRefreshTokenService {

	String createRefreshToken(int user_id);
	TokenRefreshResponse getRefreshTokenByToken(String token);
	
	boolean verifyExpiration(RefreshToken token);
	
	void DeleteTokenByUser(int id);
}
