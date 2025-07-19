package com.challengeraven.calculator.app.service;

import java.util.List;

import com.challengeraven.calculator.app.dto.ParametersOperation;
import com.challengeraven.calculator.app.dto.ResponseOperationDTO;
import com.challengeraven.calculator.app.entity.OperationEntity;

public interface OperationService {
	
	ResponseOperationDTO findById(Long id);
	
	List<OperationEntity> findAllOperationList();

	ResponseOperationDTO calculate(ParametersOperation request, Long userId);
	
	void DeleteById(Long id);
}
