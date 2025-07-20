package com.challengeraven.calculator.app.service;

import com.challengeraven.calculator.app.dto.JwtAuthenticationResponse;
import com.challengeraven.calculator.app.dto.RefreshTokenRequest;
import com.challengeraven.calculator.app.dto.ResponseSignUpDTO;
import com.challengeraven.calculator.app.dto.SignInRequest;
import com.challengeraven.calculator.app.dto.SignUpRequest;
import com.challengeraven.calculator.app.entity.UserEntity;

public interface UserService {
	
	UserEntity findByUsername(String username);
	
	ResponseSignUpDTO signUp(SignUpRequest signUpRequest);
	
	JwtAuthenticationResponse siginin(SignInRequest siginRequest);
	
	JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
