package com.ibm.hack.rabbot.rabbottest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ibm.hack.rabbot.rabbottest.entity.InterviewDetails;

@Repository
public interface InterviewDetailsRepository extends JpaRepository<InterviewDetails, Long>{

	@Query(value= "SELECT COUNT(0) FROM INTERVIEW_DETAILS WHERE INTERVIEW_CODE =:interviewId AND SUBJECT =:subject",nativeQuery = true)
	int getScore(@Param("interviewId") String interviewId, @Param("subject")String subject);

}
