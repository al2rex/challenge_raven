package com.challengeraven.calculator.app.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.challengeraven.calculator.app.dto.JwtAuthenticationResponse;
import com.challengeraven.calculator.app.dto.ResponseSignUpDTO;
import com.challengeraven.calculator.app.dto.SignInRequest;
import com.challengeraven.calculator.app.dto.SignUpRequest;
import com.challengeraven.calculator.app.entity.UserEntity;
import com.challengeraven.calculator.app.repository.UserRepository;
import com.challengeraven.calculator.app.service.JWTService;
import com.challengeraven.calculator.app.utils.OperationMapper;


public class UserServiceImplTest {
	@Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JWTService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private OperationMapper operationMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSignUp_shouldReturnResponseSignUpDTO() {
        SignUpRequest request = new SignUpRequest();
        request.setUsername("aldo");
        request.setEmail("aldo@test.com");
        request.setPassword("1234");

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(request.getUsername());
        userEntity.setEmail(request.getEmail());
        userEntity.setCreatedAt(LocalDate.now());

        UserEntity savedUser = new UserEntity();
        savedUser.setId(1L);
        savedUser.setUsername("aldo");
        savedUser.setEmail("aldo@test.com");
        savedUser.setCreatedAt(LocalDate.now());

        ResponseSignUpDTO responseDTO = new ResponseSignUpDTO();
        responseDTO.setUsername("aldo");
        responseDTO.setEmail("aldo@test.com");

        when(passwordEncoder.encode("1234")).thenReturn("encoded1234");
        when(userRepository.save(any(UserEntity.class))).thenReturn(savedUser);
        when(operationMapper.fromUserEntityToResponseSignUpDTO(savedUser)).thenReturn(responseDTO);

        ResponseSignUpDTO result = userService.signUp(request);

        assertNotNull(result);
        assertEquals("aldo", result.getUsername());
        assertEquals("aldo@test.com", result.getEmail());
    }

    @Test
    void testSiginin_shouldReturnJwtAuthenticationResponse() {
        SignInRequest request = new SignInRequest();
        request.setUsername("aldo");
        request.setPassword("1234");

        UserEntity user = new UserEntity();
        user.setUsername("aldo");
        user.setPassword("1234");

        when(userRepository.findByUsername("aldo")).thenReturn(Optional.of(user));
        when(jwtService.generateToken(user)).thenReturn("token");
        

        JwtAuthenticationResponse result = userService.siginin(request);

        assertNotNull(result);
        assertEquals("token", result.getToken());
    }


    @Test
    void testFindByUsername_shouldReturnUserEntity() {
        UserEntity user = new UserEntity();
        user.setUsername("aldo");

        when(userRepository.findByUsername("aldo")).thenReturn(Optional.of(user));

        UserEntity result = userService.findByUsername("aldo");

        assertNotNull(result);
        assertEquals("aldo", result.getUsername());
    }

    @Test
    void testFindByUsername_shouldReturnNullWhenNotFound() {
        when(userRepository.findByUsername("notfound")).thenReturn(Optional.empty());

        UserEntity result = userService.findByUsername("notfound");

        assertNull(result);
    }
}
