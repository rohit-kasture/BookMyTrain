package com.app.userservice.controllers;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.userservice.config.JwtTokenUtil;
import com.app.userservice.model.CreateUserRequestModel;
import com.app.userservice.model.CreateUserResponseModel;
import com.app.userservice.model.JwtResponse;
import com.app.userservice.model.LoginRequestModel;
import com.app.userservice.model.UserDto;
import com.app.userservice.service.JwtUserDetailsService;
import com.app.userservice.service.UserService;

@RestController
@CrossOrigin
public class JwtAuthenticationController {
	private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationController.class);

	@Autowired
	UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequestModel authenticationRequest)
			throws Exception {
		LOGGER.info(" Authenticating the user : '{}'" + authenticationRequest.getEmail());

		authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());

		final String token = jwtTokenUtil.generateToken(userDetails);
		LOGGER.info("Authenticated Successfully.");
		return ResponseEntity.ok(new JwtResponse(token));

	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<CreateUserResponseModel> saveUser(@RequestBody CreateUserRequestModel createUserRequestModel)
			throws Exception {
		LOGGER.info("Registering user : {}"+createUserRequestModel.getEmail());

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserDto userDto = modelMapper.map(createUserRequestModel, UserDto.class);
		userService.createUser(userDto);
		CreateUserResponseModel returnval = modelMapper.map(userDto, CreateUserResponseModel.class);
		LOGGER.info("Registeration of user : '{}'"+createUserRequestModel.getEmail()+"successful.");
		return ResponseEntity.status(HttpStatus.CREATED).body(returnval);
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
