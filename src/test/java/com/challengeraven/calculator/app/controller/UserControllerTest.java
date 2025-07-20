package com.challengeraven.calculator.app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.challengeraven.calculator.app.dto.JwtAuthenticationResponse;
import com.challengeraven.calculator.app.dto.ResponseSignUpDTO;
import com.challengeraven.calculator.app.dto.SignInRequest;
import com.challengeraven.calculator.app.dto.SignUpRequest;
import com.challengeraven.calculator.app.service.UserService;
import com.challengeraven.calculator.app.utils.Validator;


@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
	@Mock
    private Validator validator;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    void signUp_ShouldReturnResponseEntityWithCreatedUser() {
        // Arrange
        SignUpRequest request = new SignUpRequest();
        request.setEmail("test@example.com");

        ResponseSignUpDTO mockResponse = new ResponseSignUpDTO();
        mockResponse.setUsername("test");

        when(userService.signUp(request)).thenReturn(mockResponse);

        // Act
        ResponseEntity<ResponseSignUpDTO> response = userController.signUp(request);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("test", response.getBody().getUsername());
        verify(validator).validationEmail("test@example.com");
    }

    @Test
    void signIn_ShouldReturnJwtAuthenticationResponse() {
        // Arrange
        SignInRequest request = new SignInRequest();
        request.setUsername("user");
        request.setPassword("pass");

        JwtAuthenticationResponse mockResponse = new JwtAuthenticationResponse();
        mockResponse.setToken("abc123");

        when(userService.siginin(request)).thenReturn(mockResponse);

        // Act
        ResponseEntity<JwtAuthenticationResponse> response = userController.signIn(request);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("abc123", response.getBody().getToken());
    }

    
}
