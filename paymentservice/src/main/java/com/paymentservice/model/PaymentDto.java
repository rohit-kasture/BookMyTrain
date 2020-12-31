package com.paymentservice.model;

public class PaymentDto {
	private int insuranceId;
	private int ticketId;
	private String status;

	public PaymentDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "PaymentDto [insuranceId=" + insuranceId + ", ticketId=" + ticketId + ", status=" + status + "]";
	}

	public int getInsuranceId() {
		return insuranceId;
	}

	public void setInsuranceId(int insuranceId) {
		this.insuranceId = insuranceId;
	}

	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
