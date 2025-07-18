package com.challengeraven.calculator.app.service.impl;

import java.time.LocalDate;
import java.util.HashMap;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.challengeraven.calculator.app.dto.JwtAuthenticationResponse;
import com.challengeraven.calculator.app.dto.RefreshTokenRequest;
import com.challengeraven.calculator.app.dto.SiginRequest;
import com.challengeraven.calculator.app.dto.SignUpRequest;
import com.challengeraven.calculator.app.entity.UserEntity;
import com.challengeraven.calculator.app.repository.UserRepository;
import com.challengeraven.calculator.app.service.JWTService;
import com.challengeraven.calculator.app.service.UserService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service
@Getter
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;
	
	private final PasswordEncoder passwordEncoder;
	
	private final JWTService jwtService;
	
	private final AuthenticationManager authenticationManager;


	@Override
	public UserEntity signUp(SignUpRequest signUpRequest) {
		UserEntity user = new UserEntity();
		
		user.setUsername(signUpRequest.getUsername());
		user.setEmail(signUpRequest.getEmail());
		user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
		user.setCreatedAt(LocalDate.now());
		return userRepository.save(user);
	}


	@Override
	public JwtAuthenticationResponse siginin(SiginRequest siginRequest) {
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(siginRequest.getEmail(), 
				siginRequest.getPassword());
		
		authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		
		var user = userRepository.findByEmail(siginRequest.getEmail()).orElseThrow(() -> new IllegalArgumentException("invalid email or password"));
		var jwt = jwtService.generateToken(user);
		var refreshToken = jwtService.refreshTokenClaims(new HashMap<>(), user);
		
		JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
		jwtAuthenticationResponse.setToken(jwt);
		jwtAuthenticationResponse.setRefreshToken(refreshToken);
		return jwtAuthenticationResponse;
	}


	@Override
	public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
		String userEmail = jwtService.extractUsername(refreshTokenRequest.getToken());
		
		UserEntity user = userRepository.findByEmail(userEmail).orElseThrow();
		if( jwtService.isTokenValid(refreshTokenRequest.getToken(), user) ){
			var jwt = jwtService.generateToken(user);
			
			JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
			jwtAuthenticationResponse.setToken(jwt);
			jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());
			return jwtAuthenticationResponse;
		}
		
		return null;
	}
	
	
}
