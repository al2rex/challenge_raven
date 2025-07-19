package com.challengeraven.calculator.app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.challengeraven.calculator.app.entity.OperationEntity;
import com.challengeraven.calculator.app.entity.UserEntity;
import com.challengeraven.calculator.app.service.OperationService;
import com.challengeraven.calculator.app.service.UserService;
import com.challengeraven.calculator.app.utils.Validator;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "Calculate Controller", description = "Controlador relacionado con las operaciones SUMA, RESTA, "
		+ "MULTIPLICACION, DIVISION, RAIZ CUADRADA y el historico de las operaciones")
public class CalculateController {
	
	private final OperationService operationService;
	
	private final UserService userService;
	
	private final Validator validator;
	
	public static final Logger logger = LoggerFactory.getLogger(CalculateController.class);
	
	@PostMapping("/calculate")
	public ResponseEntity<ResponseOperationDTO> calculate(@Valid @RequestBody ParametersOperation request, Authentication authentication) {
		validator.validateOperands(request);
		String username = ((UserEntity) authentication.getPrincipal()).getUsername();
		UserEntity user = userService.findByUsername(username);
		ResponseOperationDTO response = operationService.calculate(request, user.getId());
	    return ResponseEntity.ok(response);
	}

	
	@GetMapping("/history")
	public ResponseEntity<List<OperationEntity>> signIn(){
		return ResponseEntity.ok(operationService.findAllOperationList());
	}
	
	@GetMapping("/history/{id}")
	public ResponseEntity<ResponseOperationDTO> historyById(@PathVariable Long id){
		logger.info("Inicia Controller encontrar historico ID: {}", id);
		logger.info("Termina Controller encontrar historico ID: {}", id);
		return ResponseEntity.ok(operationService.findById(id));
	}
	
	@DeleteMapping("/history/{id}")
	public void deleteHistory(@PathVariable Long id){
		logger.info("Inicia Controller Eliminacion historico ID: {}", id);
		operationService.DeleteById(id);
		logger.info("Termina Controller Eliminacion historico ID: {}", id);
	}
}
