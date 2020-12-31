package com.paymentservice.paymentcomponent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paymentservice.model.PaymentRequestModel;
import com.paymentservice.serviceimpl.PaymentServiceImpl;

@Component
public class PaymentComponent {
	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentComponent.class);

	@Autowired
	PaymentServiceImpl paymentServiceImpl;

	@JmsListener(destination = "order.queue", containerFactory = "queueConnectionFactory")
	public void consume(String paymentRequestModelString) throws JsonMappingException, JsonProcessingException {
		LOGGER.info("PAYMENT LISTNER IS LISTENING TO ORDER QUEUE DATA : '{}'" + paymentRequestModelString);
		ObjectMapper mapper = new ObjectMapper();
		PaymentRequestModel paymentRequestModel = mapper.readValue(paymentRequestModelString,
				PaymentRequestModel.class);
		paymentServiceImpl.saveAndSend(paymentRequestModel);
	}
}
