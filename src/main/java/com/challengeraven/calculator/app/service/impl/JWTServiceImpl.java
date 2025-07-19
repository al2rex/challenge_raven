package com.challengeraven.calculator.app.service.impl;

import java.util.Date;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.challengeraven.calculator.app.dto.JwtAuthenticationResponse;
import com.challengeraven.calculator.app.dto.RefreshTokenRequest;
import com.challengeraven.calculator.app.entity.UserEntity;
import com.challengeraven.calculator.app.repository.UserRepository;
import com.challengeraven.calculator.app.service.JWTService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.security.Key;
import java.util.function.Function;
import javax.crypto.SecretKey;

@Service
@Getter
@RequiredArgsConstructor
public class JWTServiceImpl implements JWTService {
	
	private final UserRepository userRepository;

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
		byte[] key = Decoders.BASE64.decode("g5jK8zE2aY1XqRw9LsNp3vT0F7dUJ4Hl6VbM1WQrST8BcZkJp2");
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
	public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
		String userEmail = extractUsername(refreshTokenRequest.getToken());

        UserEntity user = userRepository.findByEmail(userEmail).orElseThrow();
        if (isTokenValid(refreshTokenRequest.getToken(), user)) {
            var jwt = generateToken(user);

            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());
            return jwtAuthenticationResponse;
        }

        return null;
	}



	@Override
	public String refreshTokenClaims(Map<String, Object> extraClaims, UserDetails userDetails) {
		
		return generateToken(userDetails);
	}

	
}
