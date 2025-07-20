package com.challengeraven.calculator.app.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import com.challengeraven.calculator.app.dto.JwtAuthenticationResponse;
import com.challengeraven.calculator.app.dto.RefreshTokenRequest;
import com.challengeraven.calculator.app.entity.UserEntity;
import com.challengeraven.calculator.app.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class JWTServiceImplTest {
	@Mock
    private UserRepository userRepository;

    @InjectMocks
    private JWTServiceImpl jwtService;

    @Mock
    private UserEntity user;

    @Mock
    private UserDetails userDetails;

    private String token;

    @BeforeEach
    void setUp() {
        when(userDetails.getUsername()).thenReturn("testuser");
        token = jwtService.generateToken(userDetails); // genera un token válido
    }

    @Test
    void testGenerateToken() {
        String token = jwtService.generateToken(userDetails);
        assertNotNull(token);
    }

    @Test
    void testExtractUsername() {
        String username = jwtService.extractUsername(token);
        assertEquals("testuser", username);
    }

    @Test
    void testIsTokenValid_True() {
        boolean valid = jwtService.isTokenValid(token, userDetails);
        assertTrue(valid);
    }

    @Test
    void testIsTokenValid_False() {
        UserDetails anotherUser = mock(UserDetails.class);
        when(anotherUser.getUsername()).thenReturn("anotheruser");
        assertFalse(jwtService.isTokenValid(token, anotherUser));
    }

}
