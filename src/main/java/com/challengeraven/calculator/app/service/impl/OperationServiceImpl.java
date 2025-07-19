package com.challengeraven.calculator.app.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.challengeraven.calculator.app.dto.ParametersOperation;
import com.challengeraven.calculator.app.dto.ResponseOperationDTO;
import com.challengeraven.calculator.app.entity.OperationEntity;
import com.challengeraven.calculator.app.repository.OperationRepository;
import com.challengeraven.calculator.app.service.OperationService;
import com.challengeraven.calculator.app.utils.OperationMapper;
import com.challengeraven.calculator.app.utils.Validator;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Service
@Getter
@AllArgsConstructor
public class OperationServiceImpl implements OperationService {

	private final Validator validator;

	private final OperationMapper operationMapper;

	private final OperationRepository operationRepository;

	public static final Logger logger = LoggerFactory.getLogger(OperationServiceImpl.class);

	@Override
	@Transactional
	public ResponseOperationDTO calculate(ParametersOperation request, Long userId) {
		BigDecimal result = switch (request.getOperation()) {
		case ADD -> request.getOperandA().add(request.getOperandB());
		case SUBTRACT -> request.getOperandA().subtract(request.getOperandB());
		case MULTIPLY -> request.getOperandA().multiply(request.getOperandB());
		case DIVIDE -> request.getOperandA().divide(request.getOperandB(), 10, RoundingMode.HALF_UP);
		case SQRT -> validator.sqrt(request.getOperandA());
		};

		OperationEntity entity = new OperationEntity();
		entity.setOperation(request.getOperation());
		entity.setOperandA(request.getOperandA());
		entity.setOperandB(request.getOperandB());
		entity.setResult(result);
		entity.setTimestamp(LocalDateTime.now().toString());
		entity.setUsuario(operationMapper.fromIdUser(userId));

		entity = operationRepository.save(entity);

		return operationMapper.toDTO(entity);
	}

	@Override
	public ResponseOperationDTO findById(Long id) {
		logger.info("Inicia busqueda operation service por ID {}", id);

		OperationEntity operationFind = operationRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Operación con ID " + id + " no encontrada"));
		ResponseOperationDTO responseOperationDTO = operationMapper
				.fromOperationEntityTOResponseOperationDTO(operationFind);
		logger.info("Resultado econtrado operation service {}", responseOperationDTO);

		logger.info("Termina busqueda operation service por ID {}", id);

		return responseOperationDTO;
	}

	@Override
	public Page<ResponseOperationDTO> findAllOperationList(Pageable pageable) {
		logger.info("Inicia service lista operaciones");
		
		Page<OperationEntity> operationsPage = operationRepository.findAll(pageable);

	    Page<ResponseOperationDTO> dtoPage = operationsPage.map(operationMapper::toDTO);


		logger.info("Termina service lista operaciones");

		return dtoPage;
	}

	@Override
	public void DeleteById(Long id) {
		logger.info("Inicia service eliminar operation por ID {}", id);
		
		operationRepository.deleteById(id);
		
		logger.info("Termina service eliminar operation por ID {}", id);
	}
}
