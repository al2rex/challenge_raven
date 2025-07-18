package com.challengeraven.calculator.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;


@OpenAPIDefinition(
	    info = @Info(
	        title = "Mi API de Calculadora",
	        version = "1.0",
	        description = "Documentación de la API REST de Calculator"
	    )
	)
@SpringBootApplication
public class CalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run (CalculatorApplication.class, args);
	}

}
