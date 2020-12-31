package com.app.userservice.model;
public class TicketStausRequestModel {

	private String userId;
	private String trainnumber;
	private String date;

	public TicketStausRequestModel(String userId, String trainnumber, String date) {
		super();
		this.userId = userId;
		this.trainnumber = trainnumber;
		this.date = date;
	}

	public String getTrainnumber() {
		return trainnumber;
	}

	public void setTrainnumber(String trainnumber) {
		this.trainnumber = trainnumber;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

}
