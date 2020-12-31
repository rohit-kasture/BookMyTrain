package com.app.userservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.userservice.model.TicketRequestModel;
import com.app.userservice.model.TicketResponseModel;
import com.app.userservice.model.TicketStausRequestModel;

@FeignClient(name = "ticket-service")
public interface TicketAPI {

	@GetMapping(value = "/tickets/status")
	public TicketResponseModel getStatus(TicketStausRequestModel ticketStausRequestModel);

	@PostMapping(value = "/tickets/book")
	public void bookTrainTicket(TicketRequestModel ticketRequestModel);

}
