package com.springSecurity.utils;


import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtUtil {
	
	
	private  String jwtSecret ="rzxlszyykpbgqcflzxsqcysyhljt";
	
	private  int ExprirationInms =1*60*1000;
	
	 private SecretKey secretKey;
	@PostConstruct
    public void init() {
		var secret = Base64.getEncoder().encodeToString(jwtSecret.getBytes());
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }
	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	
	
	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}
	
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolve) {
		final Claims claims = extractAllClaims(token);
		return claimsResolve.apply(claims);
	}
	
	
	
	private Claims extractAllClaims(String token) {
		 return Jwts.parser()
		        .verifyWith(secretKey)
		        .build()
		        .parseSignedClaims(token)
		        .getPayload();
	}
	
	public String generateToken(String username) {
		Map<String, Object> claims = new HashMap<String, Object>();
		return createToken(claims, username);
	}
	
	private String createToken(Map<String, Object> claims , String subject) {
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
		return extractExpiration(token).before(new Date(System.currentTimeMillis()));
	}
	
	public boolean validateToken(String token , UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && isTokenExpired(token));
	}
	
	
}
