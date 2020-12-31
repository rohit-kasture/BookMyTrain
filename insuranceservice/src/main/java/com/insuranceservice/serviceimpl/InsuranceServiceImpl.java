package com.insuranceservice.serviceimpl;

import java.util.Optional;

import org.apache.activemq.command.ActiveMQQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.insuranceservice.insurancemodel.InsuranceDto;
import com.insuranceservice.insurancemodel.InsuranceEntity;
import com.insuranceservice.insurancemodel.InsuranceRequestModel;
import com.insuranceservice.insurancemodel.PaymentStatusRequestModel;
import com.insuranceservice.repository.InsuranceRepository;

@Service
public class InsuranceServiceImpl {
	private static final Logger LOGGER = LoggerFactory.getLogger(InsuranceServiceImpl.class);

	@Autowired
	InsuranceRepository insuranceRepository;

	@Autowired
	private JmsMessagingTemplate jmsTemplate;

	ObjectMapper Obj = new ObjectMapper();

	public void saveAndSend(InsuranceRequestModel insuranceRequestModel) throws JsonProcessingException {
		InsuranceEntity insuranceEntity = convertToInsuranceEntity(insuranceRequestModel);
		LOGGER.info("SAVING TICKET QUEUE DATA TO INSURANCE DATABASE WITH STATUS: '{}'" + insuranceEntity.getStatus());
		insuranceRepository.save(insuranceEntity);
		InsuranceDto insuranceDto = convertToInsuranceDto(insuranceEntity);
		String insuranceDtoStr = Obj.writeValueAsString(insuranceDto);
		LOGGER.info("PUSHING TO ORDER QUEUE THE DATA: '{}'" + insuranceDtoStr);
		jmsTemplate.convertAndSend(new ActiveMQQueue("order.queue"), insuranceDtoStr);
	}

	private InsuranceDto convertToInsuranceDto(InsuranceEntity insuranceEntity) {
		InsuranceDto insuranceDto = new InsuranceDto();
		insuranceDto.setInsuranceId(insuranceEntity.getInsuranceId());
		insuranceDto.setStatus(insuranceEntity.getStatus());
		insuranceDto.setTicketId(insuranceEntity.getTicketId());
		return insuranceDto;

	}

	private InsuranceEntity convertToInsuranceEntity(InsuranceRequestModel insuranceRequestModel) {
		InsuranceEntity insuranceEntity = new InsuranceEntity();
		insuranceEntity.setInsuranceCost(10);
		insuranceEntity.setTotalCost(insuranceEntity.getInsuranceCost() + insuranceRequestModel.getTicketCost());
		insuranceEntity.setStatus(insuranceRequestModel.getStatus());
		insuranceEntity.setTicketId(insuranceRequestModel.getTicketId());
		insuranceEntity.setUserId(insuranceRequestModel.getUserId());
		insuranceEntity.setTicketCost(insuranceRequestModel.getTicketCost());
		return insuranceEntity;
	}

	public void saveAndSendStatus(PaymentStatusRequestModel paymentStatusRequestModel) {
		Optional<InsuranceEntity> insuranceEntity = insuranceRepository
				.findById(paymentStatusRequestModel.getInsuranceId());
		if (insuranceEntity.isPresent()) {
			InsuranceEntity insuranceEntity2 = insuranceEntity.get();
			if (paymentStatusRequestModel.getStatus().equals("Accepted")) {
				insuranceEntity2.setStatus("Booked");
				insuranceRepository.save(insuranceEntity2);
			}
		}
	}
}
