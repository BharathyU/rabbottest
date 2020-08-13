package com.ibm.hack.rabbot.rabbottest.model;


import com.fasterxml.jackson.annotation.JsonProperty;


public class RabbotRequest {
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	@JsonProperty("interviewId")
	private String id;
	
	@JsonProperty("operation")
	private String operation;
	

}
