package com.challengeraven.calculator.app.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParametersOperation {
	private BigDecimal operation;
	private BigDecimal operandA;
	private BigDecimal operandB;
}
