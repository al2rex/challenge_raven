package com.challengeraven.calculator.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.challengeraven.calculator.app.entity.OperationEntity;

@Repository
public interface OperationRepository extends CrudRepository<OperationEntity, Long>{
	
}
