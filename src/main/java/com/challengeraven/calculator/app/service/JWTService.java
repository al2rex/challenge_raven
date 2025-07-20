package com.challengeraven.calculator.app.service;

import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;

public interface JWTService {
	String generateToken(UserDetails userDetails);
	
	String extractUsername(String token);
	
	Boolean isTokenValid(String token, UserDetails userDetails);
	
	String refreshTokenClaims(Map<String, Object> extraClaims, UserDetails userDetails);
}
