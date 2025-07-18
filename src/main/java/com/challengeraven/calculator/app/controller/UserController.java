package com.challengeraven.calculator.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challengeraven.calculator.app.dto.JwtAuthenticationResponse;
import com.challengeraven.calculator.app.dto.RefreshTokenRequest;
import com.challengeraven.calculator.app.dto.SiginRequest;
import com.challengeraven.calculator.app.dto.SignUpRequest;
import com.challengeraven.calculator.app.entity.UserEntity;
import com.challengeraven.calculator.app.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {
	
	private final UserService userService;

	@PostMapping("/register")
	public ResponseEntity<UserEntity> signUp(@RequestBody SignUpRequest signUpRequest){
		return ResponseEntity.ok(userService.signUp(signUpRequest));
	}
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthenticationResponse> signIn(@RequestBody SiginRequest siginRequest){
		return ResponseEntity.ok(userService.siginin(siginRequest));
	}
	
	@PostMapping("/refresh")
	public ResponseEntity<JwtAuthenticationResponse> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest){
		return ResponseEntity.ok(userService.refreshToken(refreshTokenRequest));
	}
}
