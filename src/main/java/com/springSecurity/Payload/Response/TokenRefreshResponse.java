package com.springSecurity.Payload.Response;

import lombok.Data;

@Data
public class TokenRefreshResponse {

	private String accessToken;
	
	private String refreshToken;
	
}
