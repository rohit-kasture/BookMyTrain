package com.insuranceservice.insurancemodel;

public class InsuranceRequestModel {
	private String userId;
	private int ticketId;
	private int ticketCost;
	private String status;

	public InsuranceRequestModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "InsuranceRequestModel [userId=" + userId + ", ticketId=" + ticketId + ", ticketCost=" + ticketCost
				+ ", status=" + status + "]";
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getTicketCost() {
		return ticketCost;
	}

	public void setTicketCost(int ticketCost) {
		this.ticketCost = ticketCost;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

}
