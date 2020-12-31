package com.paymentservice.repository;


import org.springframework.data.repository.CrudRepository;

import com.paymentservice.model.PaymentEntity;


public interface PaymentRepository extends CrudRepository<PaymentEntity, Long> {
	
}
