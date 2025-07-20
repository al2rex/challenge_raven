package com.challengeraven.calculator.app.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import com.challengeraven.calculator.app.config.TypeOperationEnum;
import com.challengeraven.calculator.app.dto.ParametersOperation;
import com.challengeraven.calculator.app.dto.ResponseOperationDTO;
import com.challengeraven.calculator.app.entity.UserEntity;
import com.challengeraven.calculator.app.service.OperationService;
import com.challengeraven.calculator.app.service.UserService;
import com.challengeraven.calculator.app.utils.Validator;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CalculateControllerTest {
	@InjectMocks
    private CalculateController calculateController;

    @Mock
    private OperationService operationService;

    @Mock
    private UserService userService;

    @Mock
    private Validator validator;

    @Mock
    private Authentication authentication;

    @Mock
    private UserEntity userEntity;

    @Test
    void testCalculate() {
        
        ParametersOperation request = new ParametersOperation();
        request.setOperandA(BigDecimal.valueOf(5));
        request.setOperandB(BigDecimal.valueOf(2));
        request.setOperation(TypeOperationEnum.ADD);

        String username = "aldo";
        Long userId = 123L;

        ResponseOperationDTO responseDTO = new ResponseOperationDTO();
        responseDTO.setResult(BigDecimal.valueOf(7));

        
        when(authentication.getPrincipal()).thenReturn(userEntity);
        when(userEntity.getUsername()).thenReturn(username);
        when(userService.findByUsername(username)).thenReturn(userEntity);
        when(userEntity.getId()).thenReturn(userId);
        when(operationService.calculate(request, userId)).thenReturn(responseDTO);

        
        ResponseEntity<ResponseOperationDTO> response = calculateController.calculate(request, authentication);

        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());

        verify(validator).validateOperands(request);
        verify(operationService).calculate(request, userId);
    }

    @Test
    void testOperationHistoryList() {
        
        Pageable pageable = PageRequest.of(0, 10);
        Page<ResponseOperationDTO> mockPage = new PageImpl<>(List.of(new ResponseOperationDTO()));
        when(operationService.findAllOperationList(pageable)).thenReturn(mockPage);

        
        ResponseEntity<Page<ResponseOperationDTO>> response = calculateController.operatioonHistoryList(pageable);

        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockPage, response.getBody());
    }

    @Test
    void testHistoryById() {
        
        Long id = 1L;
        ResponseOperationDTO dto = new ResponseOperationDTO();
        when(operationService.findById(id)).thenReturn(dto);

        
        ResponseEntity<ResponseOperationDTO> response = calculateController.historyById(id);

        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());
    }

    @Test
    void testDeleteHistory() {
        
        Long id = 1L;

        
        calculateController.deleteHistory(id);

        
        verify(operationService).DeleteById(id);
    }
}
