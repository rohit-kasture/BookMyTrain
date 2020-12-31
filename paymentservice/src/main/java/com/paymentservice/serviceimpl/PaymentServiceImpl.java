package com.paymentservice.serviceimpl;

import org.apache.activemq.command.ActiveMQTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paymentservice.model.PaymentDto;
import com.paymentservice.model.PaymentEntity;
import com.paymentservice.model.PaymentRequestModel;
import com.paymentservice.repository.PaymentRepository;

@Service
public class PaymentServiceImpl {
	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentServiceImpl.class);

	@Autowired
	PaymentRepository paymentRepository;

	@Autowired
	private JmsMessagingTemplate jmsTemplate;

	public void saveAndSend(PaymentRequestModel paymentRequestModel) throws JsonProcessingException {
		LOGGER.info("SAVING ORDER QUEUE DATA TO PAYMENT DATABASE WITH STATUS : '{}'" + paymentRequestModel.getStatus());
		PaymentEntity paymentEntity = convertToPaymentEntity(paymentRequestModel);
		paymentRepository.save(paymentEntity);
		PaymentDto paymentDto = convertToPaymentDto(paymentEntity);
		ObjectMapper Obj = new ObjectMapper();
		String paymentDtoStr = Obj.writeValueAsString(paymentDto);
		LOGGER.info("PUSHING TO PAYMENT TOPIC THE DATA: '{}'" + paymentDtoStr);
		jmsTemplate.convertAndSend(new ActiveMQTopic("payment.topic"), paymentDtoStr);
	}

	private PaymentDto convertToPaymentDto(PaymentEntity paymentEntity) {
		PaymentDto paymentDto = new PaymentDto();
		paymentDto.setTicketId(paymentEntity.getTicketId());
		paymentDto.setInsuranceId(paymentEntity.getInsuranceId());
		paymentDto.setStatus(paymentEntity.getStatus());
		return paymentDto;

	}

	private PaymentEntity convertToPaymentEntity(PaymentRequestModel PaymentRequestModel) {
		PaymentEntity paymentEntity = new PaymentEntity();
		paymentEntity.setInsuranceId(PaymentRequestModel.getInsuranceId());
		paymentEntity.setTicketId(PaymentRequestModel.getTicketId());
		paymentEntity.setStatus("Accepted");
		return paymentEntity;
	}

}
