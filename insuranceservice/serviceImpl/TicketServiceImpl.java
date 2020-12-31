package com.ticketservice.serviceImpl;

import javax.jms.Queue;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.ticketservice.model.TicketDto;
import com.ticketservice.model.TicketEntity;
import com.ticketservice.repository.TicketRepository;

@Service
public class TicketServiceImpl {

	@Autowired
	TicketRepository ticketRepository;
	
	@Autowired
	JmsTemplate jmsTemplate;

	@Autowired
	Queue queue;

	public String saveAndSend(TicketDto ticketDto) {

		ModelMapper modelMapper = new ModelMapper();
		TicketEntity ticketEntity = modelMapper.map(ticketDto, TicketEntity.class);
		jmsTemplate.convertAndSend(queue, ticketDto);

		ticketRepository.save(ticketEntity);
		return "Message published By Ticket Service";
	}
}
