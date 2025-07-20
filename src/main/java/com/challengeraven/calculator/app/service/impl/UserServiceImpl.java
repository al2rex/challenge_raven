package com.challengeraven.calculator.app.service.impl;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.challengeraven.calculator.app.dto.JwtAuthenticationResponseDTO;
import com.challengeraven.calculator.app.dto.ResponseSignUpDTO;
import com.challengeraven.calculator.app.dto.SignInRequestDTO;
import com.challengeraven.calculator.app.dto.SignUpRequestDTO;
import com.challengeraven.calculator.app.entity.UserEntity;
import com.challengeraven.calculator.app.repository.UserRepository;
import com.challengeraven.calculator.app.service.JWTService;
import com.challengeraven.calculator.app.service.UserService;
import com.challengeraven.calculator.app.utils.OperationMapper;

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
	
	private final OperationMapper operationMapper;
	
	public static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


	@Override
	public ResponseSignUpDTO signUp(SignUpRequestDTO signUpRequest) {
		
		UserEntity user = new UserEntity();
		
		user.setUsername(signUpRequest.getUsername());
		user.setEmail(signUpRequest.getEmail());
		user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
		user.setCreatedAt(LocalDate.now());
		UserEntity userSave = userRepository.save(user);
		
		return operationMapper.fromUserEntityToResponseSignUpDTO(userSave);
	}


	@Override
	public JwtAuthenticationResponseDTO siginin(SignInRequestDTO siginRequest) {
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(siginRequest.getUsername(), 
				siginRequest.getPassword());
		
		authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		
		var user = userRepository.findByUsername(siginRequest.getUsername()).orElseThrow(() -> new IllegalArgumentException("invalid username or password"));
		var jwt = jwtService.generateToken(user);
		
		
		JwtAuthenticationResponseDTO jwtAuthenticationResponse = new JwtAuthenticationResponseDTO();
		jwtAuthenticationResponse.setToken(jwt);
		
		return jwtAuthenticationResponse;
	}


	


	@Override
	public UserEntity findByUsername(String username) {
		return userRepository.findByUsername(username).orElse(null);
	}
	
	
}
