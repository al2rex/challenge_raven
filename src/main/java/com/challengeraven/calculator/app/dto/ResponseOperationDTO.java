package com.challengeraven.calculator.app.dto;

import java.math.BigDecimal;

import com.challengeraven.calculator.app.config.TypeOperationEnum;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "ResponseOperationDTO que representa los parametros para realizar una operacion")
public class ResponseOperationDTO {
	@Schema(description = "ID único de la operación", example = "123")
	private Long id;
	
	@NotNull
	private TypeOperationEnum operation;
	
	@NotNull
    @DecimalMin("-1000000")
    @DecimalMax("1000000")
	private BigDecimal operandA;
	
	@DecimalMin("-1000000")
    @DecimalMax("1000000")
	private BigDecimal operandB;
	
	private BigDecimal result;
	
	private String timestamp;
	
	private Long userId;
}
