package com.challengeraven.calculator.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challengeraven.calculator.app.dto.JwtAuthenticationResponseDTO;
import com.challengeraven.calculator.app.dto.ResponseSignUpDTO;
import com.challengeraven.calculator.app.dto.SignInRequestDTO;
import com.challengeraven.calculator.app.dto.SignUpRequestDTO;
import com.challengeraven.calculator.app.service.UserService;
import com.challengeraven.calculator.app.utils.Validator;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "User Controller", description = "Controlador relacionado la creacion y el inicio de sesión de los usuarios "
		+ "registrar usuario y obtener token de inicio de sesion")
public class UserController {
	
	private final Validator validator;
	
	private final UserService userService;
	
	@PostMapping("/register")
	@Operation(summary = "Crear usuario", description = "cuando el registro es exitoso regresa un objeto con el usuario creado, usuario, email y la fecha de creación")
	public ResponseEntity<ResponseSignUpDTO> signUp(@RequestBody SignUpRequestDTO signUpRequest){
		validator.validationEmail(signUpRequest.getEmail());
		return ResponseEntity.ok(userService.signUp(signUpRequest));
	}
	
	@PostMapping("/login")
	@Operation(summary = "Iniciar Sesion", description = "cuando el username y password ingresado son correctos devuelve un atributo token")
	public ResponseEntity<JwtAuthenticationResponseDTO> signIn(@RequestBody SignInRequestDTO siginRequest){
		return ResponseEntity.ok(userService.siginin(siginRequest));
	}

}
