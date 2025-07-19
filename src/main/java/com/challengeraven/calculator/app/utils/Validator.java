package com.challengeraven.calculator.app.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.challengeraven.calculator.app.config.Constants;
import com.challengeraven.calculator.app.config.TypeOperationEnum;
import com.challengeraven.calculator.app.dto.ParametersOperation;

@Component
public class Validator {
	
	public static final Logger logger = LoggerFactory.getLogger(Validator.class);
	
	public void validateOperands(ParametersOperation request) {
	    BigDecimal min = new BigDecimal("-1000000");
	    BigDecimal max = new BigDecimal("1000000");

	    if (request.getOperandA().compareTo(min) < 0 || request.getOperandA().compareTo(max) > 0) {
	    	logger.error("operandA fuera de rango permitido (-1,000,000 a 1,000,000)");
	        throw new IllegalArgumentException("operandA fuera de rango permitido (-1,000,000 a 1,000,000)");
	    }

	    if (request.getOperandB().compareTo(min) < 0 || request.getOperandB().compareTo(max) > 0) {
	    	logger.error("operandB fuera de rango permitido (-1,000,000 a 1,000,000)");
	        throw new IllegalArgumentException("operandB fuera de rango permitido (-1,000,000 a 1,000,000)");
	    }

	    if (request.getOperation() == TypeOperationEnum.DIVIDE &&
	        request.getOperandB().compareTo(BigDecimal.ZERO) == 0) {
	    	logger.error(Constants.MSG_DIVIDE);
	        throw new IllegalArgumentException(Constants.MSG_DIVIDE);
	    }

	    if (request.getOperation() == TypeOperationEnum.SQRT &&
	        request.getOperandA().compareTo(BigDecimal.ZERO) < 0) {
	    	logger.error(Constants.MSG_SQRT);
	        throw new IllegalArgumentException(Constants.MSG_SQRT);
	    }
	}
	
	public BigDecimal sqrt(BigDecimal value) {
		BigDecimal x = new BigDecimal(Math.sqrt(value.doubleValue()));
		return x.setScale(10, RoundingMode.HALF_UP);
	}

}
