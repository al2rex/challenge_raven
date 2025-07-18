package com.challengeraven.calculator.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challengeraven.calculator.app.dto.ParametersOperation;
import com.challengeraven.calculator.app.dto.ResponseOperationDTO;
import com.challengeraven.calculator.app.dto.SiginRequest;
import com.challengeraven.calculator.app.entity.UserEntity;
import com.challengeraven.calculator.app.service.OperationService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "Calculate Controller", description = "Operaciones relacionadas con las operaciones SUMA, RESTA, "
		+ "MULTIPLICACION, DIVISION, RAIZ CUADRADA y el historico de las operaciones")
public class CalculateController {
	
	private final OperationService operationService;
	
	@PostMapping("/calculate")
	public ResponseEntity<ResponseOperationDTO> calculate(@Valid @RequestBody ParametersOperation request, Authentication authentication) {
		Long userId = ((UserEntity) authentication.getPrincipal()).getId();
		ResponseOperationDTO response = operationService.calculate(request, userId);
	    return ResponseEntity.ok(response);
	}

	
	@GetMapping("/history")
	public ResponseEntity<ResponseOperationDTO> signIn(){
		return ResponseEntity.ok(null);
	}
	
	@GetMapping("/history/{id}")
	public ResponseEntity<ResponseOperationDTO> signIn(@PathVariable Long id){
		return ResponseEntity.ok(null);
	}
	
	@DeleteMapping("/history/{id}")
	public void signIn(@RequestBody SiginRequest siginRequest){
		
	}
}
