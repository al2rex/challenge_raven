package com.challengeraven.calculator.app.service.impl;

import org.springframework.stereotype.Service;

import com.challengeraven.calculator.app.entity.UserEntity;
import com.challengeraven.calculator.app.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	UserEntity signUp(SignUpRequest signUpRequest);
	
	JwtAuthenticationResponse siginin(SiginRequest siginRequest);
	
	JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
