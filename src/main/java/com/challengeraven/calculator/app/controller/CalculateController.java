package com.challengeraven.calculator.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challengeraven.calculator.app.dto.ResponseOperationDTO;
import com.challengeraven.calculator.app.dto.SiginRequest;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "User Controller", description = "Operaciones relacionadas con usuarios")
public class CalculateController {

	
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
