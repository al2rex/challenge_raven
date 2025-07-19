package com.challengeraven.calculator.app.dto;

import java.math.BigDecimal;

import com.challengeraven.calculator.app.config.TypeOperationEnum;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "ResponseOperationDTO que representa los parametros para realizar una operacion")
public class ResponseOperationDTO {
	@Schema(description = "ID único de la operación", example = "123")
	private Long id;
	
	private TypeOperationEnum operation;
	
	private BigDecimal operandA;
	

	private BigDecimal operandB;
	
	private BigDecimal result;
	
	private String timestamp;
	
	private String userId;
}
