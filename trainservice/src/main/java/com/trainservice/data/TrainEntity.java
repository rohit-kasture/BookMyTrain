package com.trainservice.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Trains")
public class TrainEntity implements Serializable {
	private static final long serialVersionUID = 5425515615377992788L;

	@Id
	@GeneratedValue
	private long id;

	@Column(nullable = false, length = 80)
	private String name;
	@Column(nullable = false, length = 6)
	
	private String trainnumber;

	@Column(nullable = false, length = 30)
	private String source;

	@Column(nullable = false, length = 30)
	private String destination;

	@Column(nullable = false)
	private String date;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTrainnumber() {
		return trainnumber;
	}

	public void setTrainnumber(String trainnumber) {
		this.trainnumber = trainnumber;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
