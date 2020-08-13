package com.ibm.hack.rabbot.rabbottest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.hack.rabbot.rabbottest.entity.InterviewDetails;
import com.ibm.hack.rabbot.rabbottest.repository.InterviewDetailsRepository;
import com.ibm.hack.rabbot.rabbottest.repository.UserDetailsRespository;

@Service
public class InterviewDetailsService {

	@Autowired
	private UserDetailsRespository userDetailsRepository;

	@Autowired
	private InterviewDetailsRepository interviewDetailsRepository;

	public String checkInterviewCode(String interviewCode) {
		String userName = userDetailsRepository.checkInterviewCode(interviewCode);
		if (userName != null) {
			return "InterviewCode is verified successfully";
		} else {
			return "Its not a match";
		}
	}

	public String saveAnswer(String interviewId, String subject, String answer) {

		try {
		InterviewDetails interviewDetails = new InterviewDetails(interviewId, subject, answer);
		interviewDetailsRepository.save(interviewDetails);
		return "Record is added successfully";
		} 
		catch(Exception e) {
			return "Exception in adding the record";
		}
	}

	public String getScore(String interviewId, String subject) {
	
		int score = interviewDetailsRepository.getScore(interviewId,subject);
		return "The score for subject "+subject + " is " + score;
		
	}

}
