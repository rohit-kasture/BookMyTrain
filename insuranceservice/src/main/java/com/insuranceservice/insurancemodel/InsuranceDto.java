package com.insuranceservice.insurancemodel;

public class InsuranceDto {
	private int insuranceId;
	private String status;
	private int ticketId;

	public InsuranceDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "InsuranceDto [insuranceId=" + insuranceId + ", status=" + status + ", ticketId=" + ticketId + "]";
	}

	public int getInsuranceId() {
		return insuranceId;
	}

	public void setInsuranceId(int insuranceId) {
		this.insuranceId = insuranceId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String string) {
		this.status = string;
	}

	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

}
