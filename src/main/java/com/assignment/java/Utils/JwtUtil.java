package com.assignment.java.Utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtUtil {

	
	private  String jwtSecret ="rzxlszyykpbgqcflzxsqcysyhljt";
	
	private  int ExprirationInms =60*60*1000;
	
	private SecretKey secretKey;
	
	@PostConstruct
	public void inti() {
		var secret = Base64.getEncoder().encodeToString(jwtSecret.getBytes());
		this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
	}
	
	public String extracEmail(String token) {
		return extracClaim(token, Claims::getSubject);
	}
	
	public Date extracExpiration(String token) {
		return extracClaim(token, Claims::getExpiration);
	}
	
	public <T> T extracClaim(String token, Function<Claims, T> claimsResolve) {
		final Claims claims = extracAllClaims(token);
		return claimsResolve.apply(claims);
	}
	
	private Claims extracAllClaims(String token) {
		return Jwts.parser()
					.verifyWith(secretKey)
					.build()
					.parseSignedClaims(token)
					.getPayload();
	}
	
	public String generateToken(String email) {
		Map<String, Object> claims = new HashMap<String, Object>();
		return createToken(claims, email);
	}
	
	private String createToken(Map<String, Object> claims, String subject) {
		return Jwts.builder()
					.claims()
					.subject(subject)
					.issuedAt(new Date(System.currentTimeMillis()))
					.expiration(new Date(System.currentTimeMillis() + ExprirationInms))
					.add(claims)
					.and()
					.signWith(secretKey)
					.compact();
	}
	
	public boolean isTokenExpired(String token) {
		return extracExpiration(token).before(new Date(System.currentTimeMillis()));
	}
	
	public boolean validateToken(String token, UserDetails userDetails) {
		final String email = extracEmail(token);
		return (email.equals(userDetails.getUsername()) && isTokenExpired(token));
	}
}
