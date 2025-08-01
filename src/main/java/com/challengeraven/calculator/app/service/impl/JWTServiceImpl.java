package com.challengeraven.calculator.app.service.impl;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.challengeraven.calculator.app.config.Constants;
import com.challengeraven.calculator.app.repository.UserRepository;
import com.challengeraven.calculator.app.service.JWTService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service
@Getter
@RequiredArgsConstructor
public class JWTServiceImpl implements JWTService {
	
	private final UserRepository userRepository;
	
	public static final Logger logger = LoggerFactory.getLogger(JWTServiceImpl.class);

	@Override
	public String generateToken(UserDetails userDetails) {
		 return Jwts.builder()
	                .subject(userDetails.getUsername())
	                .issuedAt(new Date(System.currentTimeMillis()))
	                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
	                .signWith(getSignInKey())
	                .compact();
	}

	

	@Override
	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	@Override
	public Boolean isTokenValid(String token, UserDetails userDetails) {
		return extractUsername(token).equals(userDetails.getUsername());
	}
	
	private Key getSignInKey() {
		byte[] key = Decoders.BASE64.decode(Constants.APP_SECRET_KEY);
		return Keys.hmacShaKeyFor(key);
	}
	
	private <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
	
	private Claims extractAllClaims(String token) {
		return Jwts.parser()
				   .verifyWith((SecretKey) getSignInKey())
				   .build()
				   .parseSignedClaims(token)
				   .getPayload();
	}


	@Override
	public String refreshTokenClaims(Map<String, Object> extraClaims, UserDetails userDetails) {
		
		return generateToken(userDetails);
	}
	
}
