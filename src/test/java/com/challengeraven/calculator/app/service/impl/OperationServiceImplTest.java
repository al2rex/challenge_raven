package com.challengeraven.calculator.app.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.challengeraven.calculator.app.config.TypeOperationEnum;
import com.challengeraven.calculator.app.dto.ParametersOperationDTO;
import com.challengeraven.calculator.app.dto.ResponseOperationDTO;
import com.challengeraven.calculator.app.entity.OperationEntity;
import com.challengeraven.calculator.app.repository.OperationRepository;
import com.challengeraven.calculator.app.utils.OperationMapper;
import com.challengeraven.calculator.app.utils.Validator;

import jakarta.persistence.EntityNotFoundException;

public class OperationServiceImplTest {
	@Mock
    private Validator validator;

    @Mock
    private OperationMapper operationMapper;

    @Mock
    private OperationRepository operationRepository;

    @InjectMocks
    private OperationServiceImpl operationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCalculate_AddOperation() {
        ParametersOperationDTO request = new ParametersOperationDTO();
        request.setOperandA(new BigDecimal("5"));
        request.setOperandB(new BigDecimal("3"));
        request.setOperation(TypeOperationEnum.ADD);

        OperationEntity savedEntity = new OperationEntity();
        savedEntity.setResult(new BigDecimal("8"));

        ResponseOperationDTO dto = new ResponseOperationDTO();
        dto.setResult(new BigDecimal("8"));

        when(operationRepository.save(any(OperationEntity.class))).thenReturn(savedEntity);
        when(operationMapper.toDTO(savedEntity)).thenReturn(dto);
        when(operationMapper.fromIdUser(1L)).thenReturn(null); 

        ResponseOperationDTO result = operationService.calculate(request, 1L);

        assertNotNull(result);
        assertEquals(new BigDecimal("8"), result.getResult());
    }

    @Test
    void testCalculate_SqrtOperation() {
        ParametersOperationDTO request = new ParametersOperationDTO();
        request.setOperandA(new BigDecimal("9"));
        request.setOperation(TypeOperationEnum.SQRT);

        BigDecimal sqrtResult = new BigDecimal("3");

        when(validator.sqrt(new BigDecimal("9"))).thenReturn(sqrtResult);
        when(operationMapper.fromIdUser(anyLong())).thenReturn(null);
        when(operationRepository.save(any(OperationEntity.class))).thenReturn(new OperationEntity());
        when(operationMapper.toDTO(any(OperationEntity.class))).thenReturn(new ResponseOperationDTO());

        ResponseOperationDTO result = operationService.calculate(request, 1L);

        assertNotNull(result);
    }

    @Test
    void testFindById_Found() {
        OperationEntity entity = new OperationEntity();
        entity.setId(1L);

        ResponseOperationDTO dto = new ResponseOperationDTO();
        dto.setId(1L);

        when(operationRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(operationMapper.fromOperationEntityTOResponseOperationDTO(entity)).thenReturn(dto);

        ResponseOperationDTO result = operationService.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void testFindById_NotFound() {
        when(operationRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> operationService.findById(1L));
    }

    @Test
    void testFindAllOperationList() {
        OperationEntity entity = new OperationEntity();
        ResponseOperationDTO dto = new ResponseOperationDTO();

        Page<OperationEntity> page = new PageImpl<>(List.of(entity));
        Pageable pageable = PageRequest.of(0, 10);

        when(operationRepository.findAll(pageable)).thenReturn(page);
        when(operationMapper.toDTO(entity)).thenReturn(dto);

        Page<ResponseOperationDTO> result = operationService.findAllOperationList(pageable);

        assertNotNull(result);
        assertEquals(1, result.getContent().size());
    }

    @Test
    void testDeleteById() {
        Long id = 1L;

        operationService.DeleteById(id);

        verify(operationRepository, times(1)).deleteById(id);
    }
}
