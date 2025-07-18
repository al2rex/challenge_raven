package com.challengeraven.calculator.app.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="Operations")
public class OperationEntity implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String userId;
	
	private String operation;
	
	private BigDecimal operandA;
	
	private BigDecimal operandB;
	
	private BigDecimal result;
	
	private String timestamp;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
