package com.trainservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.trainservice.model.CreateTrainRequestModel;
import com.trainservice.model.CreateTrainResponseModel;
import com.trainservice.model.TrainRequestModel;
import com.trainservice.model.TrainResponseModel;
import com.trainservice.serviceImpl.TrainServiceImpl;

@RestController
@RequestMapping("/trains")
public class TrainController {

	@Autowired
	TrainServiceImpl TrainServiceImpl;

	@GetMapping("/getalltrains")
	public List<CreateTrainResponseModel> getAll() throws JsonProcessingException {
		System.out.println("inside get all train controller");
		List<CreateTrainResponseModel> list = TrainServiceImpl.getallTrains();
		return list;
	}

	@PostMapping("/search")
	public List<TrainResponseModel> searchTrain(@RequestBody TrainRequestModel trainRequestModel)
			throws JsonProcessingException {

		System.out.println("inside search train controller");
		return TrainServiceImpl.searchTrain(trainRequestModel);
	}

	@PostMapping("/create")
	public CreateTrainResponseModel createNewTrain(@RequestBody CreateTrainRequestModel createTrainRequestModel) {
		CreateTrainResponseModel createTrainResponseModel = TrainServiceImpl.save(createTrainRequestModel);

		return createTrainResponseModel;
	}

	@GetMapping("/print")
	public String print() {
		return "printed";
	}

}
