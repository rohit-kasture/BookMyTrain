package com.ticketservice.repository;

import org.springframework.data.repository.CrudRepository;

import com.ticketservice.model.TicketEntity;

public interface TicketRepository extends CrudRepository<TicketEntity, Integer> {
	TicketEntity findByUserIdAndTrainnumber(String userId, String trainnumber);
}
