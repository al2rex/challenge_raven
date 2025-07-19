package com.challengeraven.calculator.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.challengeraven.calculator.app.service.UserDetailService;

import jakarta.servlet.Filter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@Component
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
	
	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	
	private final UserDetailService userDetailService;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		try {
			http.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(request -> request.requestMatchers(
						"/api/auth/**", 
						"/swagger-ui.html",
		                "/swagger-ui/**",
		                "/v3/api-docs",
		                "/v3/api-docs/**",
		                "/webjars/**")
				.permitAll()
				.anyRequest().authenticated())			
				.sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authenticationProvider(authenticationProvider()).addFilterBefore(jwtAuthenticationFilter, (Class<? extends Filter>) UsernamePasswordAuthenticationFilter.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return http.build();
	}
	
	
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(userDetailService.userDetailService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}
	
	@Bean
    public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	} 
	
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
}
