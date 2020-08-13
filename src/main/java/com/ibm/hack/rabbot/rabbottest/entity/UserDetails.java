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
@Setter
@NoArgsConstructor
@Entity
@Table(name = "USER_DETAILS")
public class UserDetails {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "USER_NAME")
	private String userName;

	@Column(name = "INTERVIEW_CODE")
	private String interviewCode;

	@Column(name = "details")
	private String details;

	public UserDetails(Long id, String userName, String interviewCode, String details) {
		super();
		this.id = id;
		this.userName = userName;
		this.interviewCode = interviewCode;
		this.details = details;
	}
}
