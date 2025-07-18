package com.challengeraven.calculator.app.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.challengeraven.calculator.app.config.TypeOperationEnum;
import com.challengeraven.calculator.app.dto.ParametersOperation;
import com.challengeraven.calculator.app.dto.ResponseOperationDTO;
import com.challengeraven.calculator.app.entity.OperationEntity;
import com.challengeraven.calculator.app.entity.UserEntity;
import com.challengeraven.calculator.app.repository.OperationRepository;
import com.challengeraven.calculator.app.service.OperationService;
import com.challengeraven.calculator.app.utils.OperationMapper;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Service
@Getter
@AllArgsConstructor
public class OperationServiceImpl implements OperationService {

	private final OperationRepository operationRepository;
	private final OperationMapper operationMapper;

	@Override
	@Transactional
	public ResponseOperationDTO calculate(ParametersOperation request, Long userId) {	
		validateOperands(request);

		BigDecimal result = switch (request.getOperation()) {
		case ADD -> request.getOperandA().add(request.getOperandB());
		case SUBTRACT -> request.getOperandA().subtract(request.getOperandB());
		case MULTIPLY -> request.getOperandA().multiply(request.getOperandB());
		case DIVIDE -> request.getOperandA().divide(request.getOperandB(), 10, RoundingMode.HALF_UP);
		case SQRT -> sqrt(request.getOperandA());
		};

		OperationEntity entity = new OperationEntity();
		entity.setOperation(request.getOperation());
		entity.setOperandA(request.getOperandA());
		entity.setOperandB(request.getOperandB());
		entity.setResult(result);
		entity.setTimestamp(LocalDateTime.now().toString());
		UserEntity u = new UserEntity();
		u.setId(userId);
		entity.setUsuario(u);

		entity = operationRepository.save(entity);

		return operationMapper.toDTO(entity);
	}

	private void validateOperands(ParametersOperation request) {
		if (request.getOperation() == TypeOperationEnum.DIVIDE
				&& BigDecimal.ZERO.compareTo(request.getOperandB()) == 0) {
			throw new IllegalArgumentException("División por cero no permitida");
		}

		if (request.getOperation() == TypeOperationEnum.SQRT && request.getOperandA().compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException("Raíz cuadrada solo de números positivos");
		}
	}

	private BigDecimal sqrt(BigDecimal value) {
		BigDecimal x = new BigDecimal(Math.sqrt(value.doubleValue()));
		return x.setScale(10, RoundingMode.HALF_UP);
	}

}
