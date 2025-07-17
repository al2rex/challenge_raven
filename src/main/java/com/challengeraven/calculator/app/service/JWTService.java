package com.challengeraven.calculator.app.service;

public interface JWTService {
	String generateToken(UserDetails userDetails);
	
	String refreshToken(Map<String, Object> extraClaims, UserDetails userDetails);
	
	String extractUsername(String token);
	
	Boolean isTokenValid(String token, UserDetails userDetails);
}
