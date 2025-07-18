package com.challengeraven.calculator.app.dto;

import java.math.BigDecimal;

import com.challengeraven.calculator.app.config.TypeOperationEnum;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParametersOperation {
	
	@NotNull
	private TypeOperationEnum operation;
	
	@NotNull
    @DecimalMin("-1000000")
    @DecimalMax("1000000")
	private BigDecimal operandA;
	
	@NotNull
    @DecimalMin("-1000000")
    @DecimalMax("1000000")
	private BigDecimal operandB;
}
