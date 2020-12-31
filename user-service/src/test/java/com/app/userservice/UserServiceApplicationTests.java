package com.app.userservice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.app.userservice.controllers.JwtAuthenticationController;
import com.app.userservice.controllers.UsersController;
import com.app.userservice.model.LoginRequestModel;
import com.app.userservice.model.TicketRequestModel;

@SpringBootTest
class UserServiceApplicationTests {
	
	private String TOKEN;

	@Autowired
	UsersController usersController;

	@Autowired
	JwtAuthenticationController jwtAuthenticationController;

	@Before
	public void before() {

	}

	@After
	public void after() {

	}

	@Test
	void testUserAuthentication() throws Exception {
		LoginRequestModel authenticationRequest = new LoginRequestModel();
		authenticationRequest.setEmail("rohit@gmail.com");
		authenticationRequest.setPassword("rohit");
		ResponseEntity<?> jwtRes = jwtAuthenticationController.createAuthenticationToken(authenticationRequest);
		TOKEN = jwtRes.getBody().toString();
		System.out.println("Generated JWT Token : " + jwtRes.getBody().toString());
		assertEquals(HttpStatus.OK, jwtRes.getStatusCode());// successfully gives green
	}
	
	private TicketRequestModel setTrainModel(TicketRequestModel ticketRequestModel) {
		ticketRequestModel.setCreationDate("");
		ticketRequestModel.setDate("2020-12-30 12:30: 40");
		ticketRequestModel.setDestination("Mumbai");
		ticketRequestModel.setNoOfSeats(4);
		ticketRequestModel.setStatus("pending");
		ticketRequestModel.setTicketCost(10000);
		ticketRequestModel.setTrainnumber("112202");
		ticketRequestModel.setUserId("ae31b227-a41a-4d65-9738-53fdb11c5995");
		ticketRequestModel.setSource("Jabalpur");
		return ticketRequestModel;
	}
}
