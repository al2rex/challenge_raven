package com.challengeraven.calculator.app.dto;

import java.math.BigDecimal;

import com.challengeraven.calculator.app.config.TypeOperationEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParametersOperationDTO {
	
	private TypeOperationEnum operation;
	
	private BigDecimal operandA;
	
	private BigDecimal operandB;
}
