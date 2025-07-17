package com.challengeraven.calculator.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.challengeraven.calculator.app.service.JWTService;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Component
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	private final JWTService jwtService;
	
	private final UserService userService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String authHeader = request.getHeader("Authorization");
		final String jwt;
		final String userEmail;
		if(StringUtils.isEmpty(authHeader) || !org.apache.commons.lang3.StringUtils.startsWith(authHeader, "Bearer")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		jwt = authHeader.substring(7);
		userEmail = jwtService.extractUsername(jwt);
		
		if(!StringUtils.isEmpty(userEmail) && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = userService.userDetailService().loadUserByUsername(userEmail);
			if(jwtService.isTokenValid(jwt, userDetails)) {
				SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
				
				UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				securityContext.setAuthentication(token);
				SecurityContextHolder.setContext(securityContext);
			}
		}
				
		filterChain.doFilter(request, response);
	}
}
