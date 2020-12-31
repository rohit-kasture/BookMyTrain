package com.app.userservice.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.app.userservice.model.UserDto;

public interface UserService extends UserDetailsService{ 
	UserDto createUser(UserDto userDetail);
	UserDto getUserDetailsByEmail(String email);
}
