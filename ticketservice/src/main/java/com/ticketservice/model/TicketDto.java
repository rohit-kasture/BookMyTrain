package com.ticketservice.model;

public class TicketDto {
	private String userId;
	private int ticketID;
	private int ticketCost;
	private String status;

	public TicketDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "InsuranceRequestModel [userId=" + userId + ", ticketID=" + ticketID + ", ticketCost=" + ticketCost
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
		return ticketID;
	}

	public void setTicketId(int ticketID) {
		this.ticketID = ticketID;
	}

}
