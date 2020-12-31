
package com.ticketservice.ticketcomponent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ticketservice.model.PaymentStatusRequestModel;
import com.ticketservice.serviceImpl.TicketServiceImpl;

@Component
public class TicketComponent {
	private static final Logger LOGGER = LoggerFactory.getLogger(TicketComponent.class);

	@Autowired
	TicketServiceImpl ticketServiceImpl;

	@JmsListener(destination = "payment.topic",containerFactory = "topicConnectionFactory")
	public void consume(String ticketRequestModelString) throws JsonMappingException, JsonProcessingException {
		LOGGER.info("TICKET SERVICE CONSUMING PAYMENT TOPIC : '{}'" + ticketRequestModelString);
		ObjectMapper mapper = new ObjectMapper();
		PaymentStatusRequestModel paymentStatusRequestModel = mapper.readValue(ticketRequestModelString,
				PaymentStatusRequestModel.class);
		ticketServiceImpl.updateServices(paymentStatusRequestModel);
	}

}
