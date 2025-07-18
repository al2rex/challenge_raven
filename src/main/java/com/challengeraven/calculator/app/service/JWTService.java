package com.challengeraven.calculator.app.service;

import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;

import com.challengeraven.calculator.app.dto.JwtAuthenticationResponse;
import com.challengeraven.calculator.app.dto.RefreshTokenRequest;

public interface JWTService {
	String generateToken(UserDetails userDetails);
	
	JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
	
	String extractUsername(String token);
	
	Boolean isTokenValid(String token, UserDetails userDetails);
	
	String refreshTokenClaims(Map<String, Object> extraClaims, UserDetails userDetails);
}
