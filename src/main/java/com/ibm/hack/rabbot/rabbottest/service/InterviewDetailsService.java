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

	public String saveAnswer(String interviewId, String subject, String answer, String data) {

		try {
			answer = updateAnswerValue(interviewId, subject, answer, data);
		InterviewDetails interviewDetails = new InterviewDetails(interviewId, subject, answer);
		interviewDetailsRepository.save(interviewDetails);
		return "Record is added successfully";
		} 
		catch(Exception e) {
			return "Exception in adding the record";
		}
	}

	private String updateAnswerValue(String interviewId, String subject, String answer, String data) {
		
		if("java1".equals(data) && "3".equals(answer) || "java2".equals(data) && "4".equals(answer) || "java3".equals(data) && "3".equals(answer) 
				|| "java4".equals(data) && "1".equals(answer)|| "java5".equals(data) && "2".equals(answer)) {
		return "correct";
		}
		if("php1".equals(data) && "3".equals(answer) || "php2".equals(data) && "4".equals(answer) || "php3".equals(data) && "3".equals(answer) 
				|| "php4".equals(data) && "1".equals(answer)|| "php5".equals(data) && "2".equals(answer)) {
			return "correct";
		}
		return "incorrect";
	}

	public String getScore(String interviewId, String subject) {
	
		int score = interviewDetailsRepository.getScore(interviewId,subject);
		if(score<3) {
			return "The score for subject "+subject + " is " + score + ".Please improve your " + subject + " skills ";
		}
		else {
			return "The score for subject "+subject + " is " + score;
		}

		
	}

}
