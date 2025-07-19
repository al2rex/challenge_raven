package com.challengeraven.calculator.app.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.challengeraven.calculator.app.config.TypeOperationEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="Operations")
public class OperationEntity implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
		
	@Enumerated(EnumType.STRING)
	private TypeOperationEnum operation;
	
	private BigDecimal operandA;
	
	private BigDecimal operandB;
	
	private BigDecimal result;
	
	private String timestamp;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private UserEntity usuario;

	private static final long serialVersionUID = 1L;

}
