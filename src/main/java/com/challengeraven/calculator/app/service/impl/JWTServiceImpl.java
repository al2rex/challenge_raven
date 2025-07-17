package com.challengeraven.calculator.app.service.impl;

import org.springframework.stereotype.Service;

import com.challengeraven.calculator.app.service.JWTService;
import com.challengeraven.calculator.app.service.Map;
import com.challengeraven.calculator.app.service.UserDetails;

@Service
public class JWTServiceImpl implements JWTService {

	@Override
	public String extractUsername(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String generateToken(UserDetails userDetails) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String refreshToken(Map<String, Object> extraClaims, UserDetails userDetails) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean isTokenValid(String token, UserDetails userDetails) {
		// TODO Auto-generated method stub
		return null;
	}

}
