package com.ticketservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ticketservice.model.TicketRequestModel;
import com.ticketservice.model.TicketResponseModel;
import com.ticketservice.model.TicketStatusRequestModel;
import com.ticketservice.serviceImpl.TicketServiceImpl;

@RestController
@RequestMapping("/tickets")
public class TicketController {
	@Autowired
	TicketServiceImpl ticketServiceImpl;

	@PostMapping("/book")
	public void book(@RequestBody TicketRequestModel ticketRequestModel) throws JsonProcessingException {
		
		ticketServiceImpl.saveAndSend(ticketRequestModel);
	}
	
	@PostMapping("/status")
	public TicketResponseModel getStatus(@RequestBody TicketStatusRequestModel ticketRequestModel) throws JsonProcessingException {
		return	ticketServiceImpl.getstatus(ticketRequestModel);
	}
	
	@GetMapping("/print")
	public String print() {
		return "printed";
	}
}
