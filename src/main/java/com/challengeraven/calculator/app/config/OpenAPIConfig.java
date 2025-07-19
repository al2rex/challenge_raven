package com.challengeraven.calculator.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
@SecurityScheme(
	    name = "bearerAuth",
	    scheme = "bearer",
	    type = SecuritySchemeType.HTTP,
	    bearerFormat = "JWT",
	    in = SecuritySchemeIn.HEADER
	)
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("API de Calculadora")
                .version("1.0")
                .description("Documentación de la API de operaciones matemáticas"));
    }
}