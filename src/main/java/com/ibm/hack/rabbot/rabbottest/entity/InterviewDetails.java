package com.ibm.hack.rabbot.rabbottest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
@Entity
@Table(name = "INTERVIEW_DETAILS")
public class InterviewDetails {
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "SUBJECT")
	private String subject;
	
	@Column(name = "ANSWER")
	private String answer;
	
	@Column(name = "INTERVIEW_CODE")
	private String interviewCode;

	public InterviewDetails(String interviewCode, String subject, String answer) {
		this.interviewCode = interviewCode;
		this.subject = subject;
		this.answer = answer;
	}
	

}
