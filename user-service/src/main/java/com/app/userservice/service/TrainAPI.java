package com.app.userservice.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.userservice.model.TicketResponseModel;
import com.app.userservice.model.TrainQueryRequestModel;
import com.app.userservice.model.TrainRequestModel;
import com.app.userservice.model.TrainResponseModel;

@FeignClient(name = "train-service")
public interface TrainAPI {

	@PostMapping(value = "/trains/search")
	public List<TrainResponseModel> searchTrain(TrainQueryRequestModel trainrequestmodel);

	@PostMapping(value = "/trains/create")
	public TrainResponseModel createTrain(TrainRequestModel createTrainRequestModel);

	@GetMapping(value = "/trains/print")
	public String print();

	@GetMapping(value = "/trains/getalltrains")
	public List<TrainResponseModel> getall();
	
	@GetMapping(value = "/trains/status")
	public TicketResponseModel getStatus();
}
