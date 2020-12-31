package com.insuranceservice.Insurancelistner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.insuranceservice.insurancemodel.InsuranceRequestModel;
import com.insuranceservice.insurancemodel.PaymentStatusRequestModel;
import com.insuranceservice.serviceimpl.InsuranceServiceImpl;

@Component
public class InsuranceComponent {
	private static final Logger LOGGER = LoggerFactory.getLogger(InsuranceComponent.class);

	@Autowired
	InsuranceServiceImpl insuranceServiceImpl;

	@JmsListener(destination = "ticket.queue", containerFactory = "queueConnectionFactory")
	public void consume(String insuranceRequestModelString) throws JsonMappingException, JsonProcessingException {
		LOGGER.info("INSURANCE SERVICE CONSUMING TICKET QUEUE : '{}'" + insuranceRequestModelString);
		ObjectMapper mapper = new ObjectMapper();
		InsuranceRequestModel insuranceRequestModel = mapper.readValue(insuranceRequestModelString,
				InsuranceRequestModel.class);
			insuranceServiceImpl.saveAndSend(insuranceRequestModel);
	}

	@JmsListener(destination = "payment.topic", containerFactory = "topicConnectionFactory")
	public void consumeStatusFromPayment(String insuranceRequestModelString)
			throws JsonMappingException, JsonProcessingException {
		LOGGER.info("INSURANCE SERVICE CONSUMING PAYMENT TOPIC : '{}'" + insuranceRequestModelString);
		ObjectMapper mapper = new ObjectMapper();
		PaymentStatusRequestModel paymentStatusRequestModel = mapper.readValue(insuranceRequestModelString,
				PaymentStatusRequestModel.class);
		insuranceServiceImpl.saveAndSendStatus(paymentStatusRequestModel);
	}
}
