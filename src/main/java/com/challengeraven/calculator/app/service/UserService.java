package com.challengeraven.calculator.app.service;

import com.challengeraven.calculator.app.dto.JwtAuthenticationResponseDTO;
import com.challengeraven.calculator.app.dto.ResponseSignUpDTO;
import com.challengeraven.calculator.app.dto.SignInRequestDTO;
import com.challengeraven.calculator.app.dto.SignUpRequestDTO;
import com.challengeraven.calculator.app.entity.UserEntity;

public interface UserService {
	
	UserEntity findByUsername(String username);
	
	ResponseSignUpDTO signUp(SignUpRequestDTO signUpRequest);
	
	JwtAuthenticationResponseDTO siginin(SignInRequestDTO siginRequest);
		
}
