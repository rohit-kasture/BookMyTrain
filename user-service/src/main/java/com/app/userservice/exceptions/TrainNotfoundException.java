package com.app.userservice.exceptions;

public class TrainNotfoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public TrainNotfoundException(String message) {
		super(message);
	}

}