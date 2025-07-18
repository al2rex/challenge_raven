package com.challengeraven.calculator.app.dto;

import java.math.BigDecimal;

import com.challengeraven.calculator.app.config.TypeOperationEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParametersOperation {
	
	private TypeOperationEnum operation;
	
	private BigDecimal operandA;
	

	private BigDecimal operandB;
}
