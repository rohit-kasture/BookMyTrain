package com.ticketservice.serviceImpl;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Optional;

import org.apache.activemq.command.ActiveMQQueue;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ticketservice.model.PaymentStatusRequestModel;
import com.ticketservice.model.TicketDto;
import com.ticketservice.model.TicketEntity;
import com.ticketservice.model.TicketRequestModel;
import com.ticketservice.model.TicketResponseModel;
import com.ticketservice.model.TicketStatusRequestModel;
import com.ticketservice.repository.TicketRepository;

@Service
public class TicketServiceImpl {
	private static final Logger LOGGER = LoggerFactory.getLogger(TicketServiceImpl.class);

	@Autowired
	TicketRepository ticketRepository;

	@Autowired
	private JmsMessagingTemplate jmsTemplate;

	ModelMapper modelMapper = new ModelMapper();
	ObjectMapper Obj = new ObjectMapper();

	public void saveAndSend(TicketRequestModel ticketRequestModel) throws JsonProcessingException {
		LOGGER.info(
				"SAVING INITIAL REQUEST DATA TO TICKET DATABASE WITH STATUS : '{}'" + ticketRequestModel.getStatus());

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		TicketEntity ticketEntity = modelMapper.map(ticketRequestModel, TicketEntity.class);
		ticketEntity.setCreationDate(Instant.now().atZone(ZoneId.of("Asia/Kolkata")).toString());
		ticketRepository.save(ticketEntity);
		TicketDto ticketDto = modelMapper.map(ticketEntity, TicketDto.class);
		String ticketDtoStr = Obj.writeValueAsString(ticketDto);
		jmsTemplate.convertAndSend(new ActiveMQQueue("ticket.queue"), ticketDtoStr);
	}

	public void updateServices(PaymentStatusRequestModel paymentStatusRequestModel) throws JsonProcessingException {
		LOGGER.info("SAVING  TOPIC DATA TO TICKET DATABASE WITH STATUS : '{}'" + paymentStatusRequestModel.getStatus());
		TicketEntity ticketEntity = new TicketEntity();
		Optional<TicketEntity> ticketEntityOpt = ticketRepository.findById(paymentStatusRequestModel.getTicketId());
		if (ticketEntityOpt.isPresent())
			ticketEntity = ticketEntityOpt.get();
		if (paymentStatusRequestModel.getStatus().equals("Accepted")) {
			ticketEntity.setStatus("Booked");
			ticketRepository.save(ticketEntity);
		}
	}

	public TicketResponseModel getstatus(TicketStatusRequestModel ticketRequestModel) {
		TicketEntity ticketEntity = ticketRepository.findByUserIdAndTrainnumber(ticketRequestModel.getUserId(),
				ticketRequestModel.getTrainnumber());
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		TicketResponseModel ticketResponseModel = modelMapper.map(ticketEntity, TicketResponseModel.class);
		return ticketResponseModel;
	}
}
