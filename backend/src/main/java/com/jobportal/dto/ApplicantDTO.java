package com.jobportal.dto;


import java.time.LocalDateTime;
import java.util.Base64;

import com.jobportal.entity.Applicant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicantDTO {
    private Long applicantId;
    private String name;
    private String email;
    private Long phone;
    private String website;
    private String resume;
    private String coverLetter;
    private LocalDateTime timestamp;
    private ApplicationStatus applicationStatus;
    private LocalDateTime interviewTime;
    
    public Applicant toEntity() {
        Applicant a = new Applicant();
        a.setApplicantId(this.getApplicantId());
        a.setName(this.getName());
        a.setEmail(this.getEmail());
        a.setPhone(this.getPhone());
        a.setWebsite(this.getWebsite());
        a.setResume(this.getResume()!=null? Base64.getDecoder().decode(this.getResume()): null);
        a.setCoverLetter(this.getCoverLetter());
        a.setTimestamp(this.getTimestamp());
        a.setApplicationStatus(this.getApplicationStatus());
        a.setInterviewTime(this.getInterviewTime());
        return a;
    }
}
