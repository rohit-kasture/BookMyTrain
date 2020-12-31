package com.paymentservice.model;

public class PaymentRequestModel {

	private int insuranceId;
	private String status;
	private int ticketId;

	public PaymentRequestModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "PaymentRequestModel [InsuranceId=" + insuranceId + ", status=" + status + ", ticketId=" + ticketId
				+ "]";
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

}
