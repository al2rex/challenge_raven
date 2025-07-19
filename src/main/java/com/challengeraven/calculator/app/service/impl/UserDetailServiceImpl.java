package com.challengeraven.calculator.app.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.challengeraven.calculator.app.repository.UserRepository;
import com.challengeraven.calculator.app.service.UserDetailService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service
@Getter
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailService {
	
	private final UserRepository userRepository;
	
	public static final Logger logger = LoggerFactory.getLogger(UserDetailServiceImpl.class);
	
	@Override
	public UserDetailsService userDetailService() {
		return new UserDetailsService() {
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				return (UserDetails) userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
			}
		};
	}

	

}
