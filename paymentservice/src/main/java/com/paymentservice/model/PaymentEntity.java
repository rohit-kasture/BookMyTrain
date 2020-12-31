package com.paymentservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Payment")
public class PaymentEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int paymentID;
	@Column
	private int insuranceId;
	@Column
	private int ticketId;
	
	@Column
	private String status;
	

	public PaymentEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setPaymentID(int paymentID) {
		this.paymentID = paymentID;
	}

	public int getInsuranceId() {
		return insuranceId;
	}

	public void setInsuranceId(int insuranceId) {
		this.insuranceId = insuranceId;
	}

	public int getPaymentID() {
		return paymentID;
	}

	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

}
