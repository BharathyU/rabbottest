package com.ibm.hack.rabbot.rabbottest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ibm.hack.rabbot.rabbottest.entity.UserDetails;

@Repository
public interface UserDetailsRespository extends JpaRepository<UserDetails, Long>{
	
	@Query(value ="SELECT user_name FROM user_details WHERE interview_code = :interviewCode",nativeQuery = true)
	String checkInterviewCode(@Param("interviewCode") String interviewCode);

}
