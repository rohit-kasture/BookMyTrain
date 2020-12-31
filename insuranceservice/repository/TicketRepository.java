package com.ticketservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ticketservice.model.TicketEntity;

public interface TicketRepository extends JpaRepository<TicketEntity, Long> {
	
}
