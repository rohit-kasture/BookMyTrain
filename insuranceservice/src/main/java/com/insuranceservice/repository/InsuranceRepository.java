package com.insuranceservice.repository;

import org.springframework.data.repository.CrudRepository;

import com.insuranceservice.insurancemodel.InsuranceEntity;

public interface InsuranceRepository extends CrudRepository<InsuranceEntity, Integer> {
	InsuranceEntity findByTicketId(Integer id);

}
