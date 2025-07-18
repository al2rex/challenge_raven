package com.challengeraven.calculator.app.service;

import com.challengeraven.calculator.app.dto.JwtAuthenticationResponse;
import com.challengeraven.calculator.app.dto.RefreshTokenRequest;
import com.challengeraven.calculator.app.dto.SiginRequest;
import com.challengeraven.calculator.app.dto.SignUpRequest;
import com.challengeraven.calculator.app.entity.UserEntity;

public interface UserService {
	
	UserEntity signUp(SignUpRequest signUpRequest);
	
	JwtAuthenticationResponse siginin(SiginRequest siginRequest);
	
	JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
