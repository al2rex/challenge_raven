package com.challengeraven.calculator.app.service;

import com.challengeraven.calculator.app.dto.ParametersOperation;
import com.challengeraven.calculator.app.dto.ResponseOperationDTO;

public interface OperationService {
	ResponseOperationDTO calculate(ParametersOperation request, Long userId);
}
