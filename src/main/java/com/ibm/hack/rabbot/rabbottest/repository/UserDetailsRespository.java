package com.ibm.hack.rabbot.rabbottest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ibm.hack.rabbot.rabbottest.entity.UserDetails;

@Repository
public interface UserDetailsRespository extends JpaRepository<UserDetails, Long>{
	
	@Query(value ="SELECT USER_NAME FROM USER_DETAILS WHERE INTERVIEW_CODE = :interviewCode",nativeQuery = true)
	String checkInterviewCode(@Param("interviewCode") String interviewCode);

}
