package com.app.userservice;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.app.userservice.controllers.JwtAuthenticationController;
import com.app.userservice.controllers.UsersController;
import com.app.userservice.model.LoginRequestModel;
import com.app.userservice.model.TicketRequestModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.app.model.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class UserServiceApplicationTests {

	private String TOKEN;

	@Autowired
	private MockMvc mvc;

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
	public void testRestApi1() throws Exception {
		String token = testUserAuthentication();
		assertNotNull(token);
		mvc.perform(MockMvcRequestBuilders.get("/users/status/check").header("Authorization", "Bearer " + token))
				.andExpect(status().isOk());
	}

	@Test
	public void testRestApi2() throws Exception {
		String token = testUserAuthentication();
		assertNotNull(token);
		mvc.perform(MockMvcRequestBuilders.get("/users/getall").header("Authorization", "Bearer " + token))
				.andExpect(status().isOk());
	}

	@Test
	public void testInsertObject() throws Exception { 
		String token = testUserAuthentication();
		assertNotNull(token);

		 mvc.perform(				 
		            MockMvcRequestBuilders.post("/users/createtrain")
		                    .contentType(MediaType.APPLICATION_JSON)
		                    .content(asJsonString(new CreateTrain()))
		                    .header("Authorization","Bearer "+token)).andExpect(status().isOk());
	}
	
	

	public static String asJsonString(CreateTrain cTrain) {
		try {
			return new ObjectMapper().writeValueAsString(cTrain);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	private String testUserAuthentication() throws Exception {
		LoginRequestModel authenticationRequest = new LoginRequestModel();
		authenticationRequest.setEmail("rohit@gmail.com");
		authenticationRequest.setPassword("rohit");
		ResponseEntity<?> jwtRes = jwtAuthenticationController.createAuthenticationToken(authenticationRequest);
		TOKEN = jwtRes.getBody().toString();
		System.out.println("****************************************************************************");
		System.out.println("Generated JWT Token : " + jwtRes.getBody().toString());
		System.out.println("****************************************************************************");

		assertEquals(HttpStatus.OK, jwtRes.getStatusCode());// successfully gives green
		return jwtRes.getBody().toString();
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
