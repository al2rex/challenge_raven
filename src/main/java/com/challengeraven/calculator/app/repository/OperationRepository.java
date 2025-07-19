package com.challengeraven.calculator.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.challengeraven.calculator.app.entity.OperationEntity;

@Repository
public interface OperationRepository extends JpaRepository<OperationEntity, Long>{
	
}
