package com.challengeraven.calculator.app.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Operacion implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String userId;
	
	private String operation;
	
	private BigDecimal operandA;
	
	private BigDecimal operandB;
	
	private BigDecimal result;
	
	private String timestamp;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public BigDecimal getOperandA() {
		return operandA;
	}

	public void setOperandA(BigDecimal operandA) {
		this.operandA = operandA;
	}

	public BigDecimal getOperandB() {
		return operandB;
	}

	public void setOperandB(BigDecimal operandB) {
		this.operandB = operandB;
	}

	public BigDecimal getResult() {
		return result;
	}

	public void setResult(BigDecimal result) {
		this.result = result;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
