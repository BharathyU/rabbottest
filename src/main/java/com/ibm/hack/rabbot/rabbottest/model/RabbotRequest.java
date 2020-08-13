package com.ibm.hack.rabbot.rabbottest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RabbotRequest {

	@JsonProperty("interviewId")
	private String id;

	@JsonProperty("operation")
	private String operation;

	@JsonProperty("subject")
	private String subject;

	@JsonProperty("answer")
	private String answer;

	@JsonProperty("data")
	private String data;

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

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
