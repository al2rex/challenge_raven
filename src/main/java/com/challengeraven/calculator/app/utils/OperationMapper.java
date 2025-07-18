package com.challengeraven.calculator.app.utils;

import org.springframework.stereotype.Component;

import com.challengeraven.calculator.app.dto.ResponseOperationDTO;
import com.challengeraven.calculator.app.entity.OperationEntity;

@Component
public class OperationMapper {
	public ResponseOperationDTO toDTO(OperationEntity entity) {
        ResponseOperationDTO dto = new ResponseOperationDTO();
        dto.setId(entity.getId());
        dto.setOperation(entity.getOperation());
        dto.setOperandA(entity.getOperandA());
        dto.setOperandB(entity.getOperandB());
        dto.setResult(entity.getResult());
        dto.setTimestamp(entity.getTimestamp().toString());
        dto.setUserId(entity.getUsuario().getId().toString());
        return dto;
    }
}
