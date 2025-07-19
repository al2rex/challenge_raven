package com.challengeraven.calculator.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import com.challengeraven.calculator.app.entity.UserEntity;
import com.challengeraven.calculator.app.service.OperationService;
import com.challengeraven.calculator.app.service.UserService;
import com.challengeraven.calculator.app.utils.Validator;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "Calculate Controller", description = "Controlador relacionado con las operaciones SUMA, RESTA, "
		+ "MULTIPLICACION, DIVISION, RAIZ CUADRADA y el historico de las operaciones")
@SecurityRequirement(name = "bearerAuth")
public class CalculateController {
	
	private final OperationService operationService;
	
	private final UserService userService;
	
	private final Validator validator;
	
	public static final Logger logger = LoggerFactory.getLogger(CalculateController.class);
	
	@PostMapping("/calculate")
	public ResponseEntity<ResponseOperationDTO> calculate(@Valid @RequestBody ParametersOperation request, 
			Authentication authentication) {
		
		logger.info("Inicia Controller calculate validacion operacion: payload {}", request);
		
		validator.validateOperands(request);
		
		logger.info("Termina Controller validacion operacion");
		
		logger.info("Inicia Controller calculate usuario autenticado: payload {}", authentication);
		
		String username = ((UserEntity) authentication.getPrincipal()).getUsername();
		
		logger.info("Termina Controller calculate usuario autenticado");
		
		logger.info("Inicia Controller calculate busqueda usuario por username {}", username);
		
		UserEntity user = userService.findByUsername(username);
		
		logger.info("Termina Controller calculate busqueda usuario encontrado: {}", user);
		
		logger.info("Inicia Controller calculate operacion: payload {}", request);
		
		ResponseOperationDTO response = operationService.calculate(request, user.getId());
		
		logger.info("Termina Controller calculate operacion ejecutado con resultado: payload {}", response);
		
		return ResponseEntity.ok(response);
	}

	
	@GetMapping("/history")
	public ResponseEntity<Page<ResponseOperationDTO>> operatioonHistoryList(
			@ParameterObject Pageable pageable) {
		Page<ResponseOperationDTO> response = operationService.findAllOperationList(pageable);
	    return ResponseEntity.ok(response);
	}
	
	@GetMapping("/history/{id}")
	public ResponseEntity<ResponseOperationDTO> historyById(@PathVariable Long id){
		logger.info("Inicia Controller encontrar historico ID: {}", id);
		return ResponseEntity.ok(operationService.findById(id));
	}
	
	@DeleteMapping("/history/{id}")
	public void deleteHistory(@PathVariable Long id){
		logger.info("Inicia Controller Eliminacion historico ID: {}", id);
		operationService.DeleteById(id);
		logger.info("Termina Controller Eliminacion historico ID: {}", id);
	}
}
