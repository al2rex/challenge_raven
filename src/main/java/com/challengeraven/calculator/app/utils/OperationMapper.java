package com.challengeraven.calculator.app.utils;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.challengeraven.calculator.app.dto.ResponseOperationDTO;
import com.challengeraven.calculator.app.entity.OperationEntity;
import com.challengeraven.calculator.app.entity.UserEntity;

import jakarta.servlet.http.HttpServletResponse;

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
	
	public void setErrorResponse(HttpServletResponse response, int status, String message) throws IOException {
	    response.setStatus(status);
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");

	    String json = String.format("{\"status\": %d, \"error\": \"%s\"}", status, message);
	    response.getWriter().write(json);
	}
	
	public UserEntity fromIdUser(Long id) {
		UserEntity user = new UserEntity();
		user.setId(id);
		return user;
	}
	
	public ResponseOperationDTO fromOperationEntityTOResponseOperationDTO(OperationEntity operationEntity){
		ResponseOperationDTO responseOperationDTO = new ResponseOperationDTO();
		responseOperationDTO.setId(operationEntity.getId());
		responseOperationDTO.setOperation(operationEntity.getOperation());
		responseOperationDTO.setOperandA(operationEntity.getOperandA());
		responseOperationDTO.setOperandB(operationEntity.getOperandB());
		responseOperationDTO.setResult(operationEntity.getResult());
		responseOperationDTO.setTimestamp(operationEntity.getTimestamp());
		responseOperationDTO.setUserId(operationEntity.getUsuario().getId().toString());
		return responseOperationDTO;
	}
	
	public List<ResponseOperationDTO> toDtoList(List<OperationEntity> operaciones) {
        return operaciones.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
