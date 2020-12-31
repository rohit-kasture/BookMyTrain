package com.app.userservice.model;

import java.util.Date;

public class ErrorResponseModel {

	private Date timestamp;
	private String message;
	

	public ErrorResponseModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ErrorResponseModel(Date timestamp, String message) {
		super();
		this.timestamp = timestamp;
		this.message = message;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
