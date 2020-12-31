package com.app.userservice.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.userservice.model.TicketRequestModel;
import com.app.userservice.model.TicketResponseModel;
import com.app.userservice.model.TicketStausRequestModel;
import com.app.userservice.model.TrainQueryRequestModel;
import com.app.userservice.model.TrainRequestModel;
import com.app.userservice.model.TrainResponseModel;
import com.app.userservice.service.TicketAPI;
import com.app.userservice.service.TrainAPI;
import com.app.userservice.service.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/users")
public class UsersController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UsersController.class);

	@Autowired
	private Environment env;

	@Autowired
	UserService userService;

	@Autowired
	TrainAPI trainAPI;

	@Autowired
	TicketAPI ticketAPI;

	@GetMapping("/status/check")
	public String status() {
		return "Working on port " + env.getProperty("local.server.port");
	}

	@HystrixCommand(fallbackMethod = "searchTrainFallback")
	@PostMapping(value = "/searchtrain")
	public List<TrainResponseModel> searchTrain(@RequestBody TrainQueryRequestModel trainrequestmodel) {
		LOGGER.info("user Searching for the Train.");

		List<TrainResponseModel> trainQueryResponseModel = trainAPI.searchTrain(trainrequestmodel);

		return trainQueryResponseModel;
	}

	@HystrixCommand(fallbackMethod = "bookTrainFallback")
	@PostMapping(value = "/booktrain")
	public ResponseEntity<TicketResponseModel> bookTrain(@RequestBody TicketRequestModel ticketRequestModel)
			throws InterruptedException {
		LOGGER.info("user Booking the Train.");
		ticketAPI.bookTrainTicket(ticketRequestModel);
		return new ResponseEntity<TicketResponseModel>(HttpStatus.ACCEPTED);
	}

	@HystrixCommand(fallbackMethod = "bookedTrainStatusFallback")
	@PostMapping(value = "/status")
	public TicketResponseModel bookedTrainStatus(@RequestBody TicketStausRequestModel ticketStausRequestModel) {
		LOGGER.info("user checking for the Booked Train status.");

		return ticketAPI.getStatus(ticketStausRequestModel);
	}

// Make this accessing by only Admin user.
	@PostMapping(value = "/createtrain")
	public TrainResponseModel createTrain(@RequestBody TrainRequestModel createTrainRequestModel) {
		LOGGER.info("Admin user create new train");

		TrainResponseModel createTrainResponseModel = trainAPI.createTrain(createTrainRequestModel);
		return createTrainResponseModel;
	}

	@GetMapping("/print")
	public String print() {
		return trainAPI.print();
	}

	@HystrixCommand(fallbackMethod = "getAll")
	@GetMapping("/getall")
	public List<TrainResponseModel> getAllTrains() {
		LOGGER.info("Searching all Train");
		List<TrainResponseModel> trainResponseModellist = trainAPI.getall();
		return trainResponseModellist;
	}

	public List<TrainResponseModel> searchTrainFallback(TrainQueryRequestModel trainrequestmodel,
			Throwable hystrixCommand) {
		LOGGER.info("bookTrainFallback throws exception message : '{}'" + hystrixCommand.getLocalizedMessage());

		return new ArrayList<TrainResponseModel>();

	}

	public List<TrainResponseModel> getAll(Throwable hystrixCommand) {
		LOGGER.info("Get all trains throws exception message : '{}'" + hystrixCommand.getLocalizedMessage());

		return new ArrayList<TrainResponseModel>();
	}

	public ResponseEntity<TicketResponseModel> bookTrainFallback(TicketRequestModel ticketRequestModel,
			Throwable hystrixCommand) {
		LOGGER.info("Train booking throws exception message : '{}'" + hystrixCommand.getLocalizedMessage());

		TicketResponseModel ticketResponseModel = new TicketResponseModel();
		ticketResponseModel.setStatus(
				"Please check and provide available trains only or book ticket after some time once server is up.");

		return new ResponseEntity<TicketResponseModel>(ticketResponseModel, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public TicketResponseModel bookedTrainStatusFallback(TicketStausRequestModel ticketStausRequestModel,
			Throwable hystrixCommand) {
		LOGGER.info(" Checking train status throws exception message : '{}'" + hystrixCommand.getLocalizedMessage());

		TicketResponseModel ticketResponseModel = new TicketResponseModel();
		ticketResponseModel.setStatus("Please check the status of your ticket after some time.");
		return ticketResponseModel;
	}
}