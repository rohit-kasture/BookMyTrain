package com.insuranceservice.insurancemodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Insurance")
public class InsuranceEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int insuranceId;

	@Column
	private int insuranceCost;
	@Column
	private int totalCost;

	@Column
	private String userId;
	@Column
	private int ticketId;
	@Column
	private int ticketCost;
	@Column
	private String status;

	@Override
	public String toString() {
		return "InsuranceEntity [insuranceId=" + insuranceId + ", insuranceCost=" + insuranceCost + ", totalCost="
				+ totalCost + ", userId=" + userId + ", ticketId=" + ticketId + ", ticketCost=" + ticketCost
				+ ", status=" + status + "]";
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

	public void setStatus(String status) {
		this.status = status;
	}

	public int getTicketCost() {
		return ticketCost;
	}

	public void setTicketCost(int ticketCost) {
		this.ticketCost = ticketCost;
	}

	public int getInsuranceCost() {
		return insuranceCost;
	}

	public void setInsuranceCost(int insuranceCost) {
		this.insuranceCost = insuranceCost;
	}

	public int getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(int totalCost) {
		this.totalCost = totalCost;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

}
