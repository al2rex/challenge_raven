package com.challengeraven.calculator.app.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.challengeraven.calculator.app.dto.ParametersOperationDTO;
import com.challengeraven.calculator.app.dto.ResponseOperationDTO;

public interface OperationService {
	
	ResponseOperationDTO findById(Long id);
	
	Page<ResponseOperationDTO> findAllOperationList(Pageable pageable);

	ResponseOperationDTO calculate(ParametersOperationDTO request, Long userId);
	
	void DeleteById(Long id);
}
