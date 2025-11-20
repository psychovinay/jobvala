package com.jobportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jobportal.dto.ApplicationStatus;
import com.jobportal.entity.Job;

public interface JobRepository extends JpaRepository<Job, Long> {
     @Query("select distinct j from Job j join j.applicants a where a.applicantId = :applicantId and a.applicationStatus = :status")
     List<Job> findByApplicantIdAndApplicationStatus(@Param("applicantId") Long applicantId, @Param("status") ApplicationStatus applicationStatus);

     List<Job> findByPostedBy(Long postedBy); 	
}
