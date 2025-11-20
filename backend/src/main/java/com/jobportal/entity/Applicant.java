package com.jobportal.entity;

import java.time.LocalDateTime;
import java.util.Base64;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import com.jobportal.dto.ApplicantDTO;
import com.jobportal.dto.ApplicationStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "applicants")
public class Applicant {
    @Id
    private Long applicantId;
    private String name;
    private String email;
    private Long phone;
    private String website;
    @Lob
    @Basic(fetch = FetchType.EAGER)
    @Column(columnDefinition = "LONGBLOB")
    private byte[] resume;
    @Lob
    @Basic(fetch = FetchType.EAGER)
    @Column(columnDefinition = "LONGTEXT")
    private String coverLetter;
    private LocalDateTime timestamp;
    private ApplicationStatus applicationStatus;
    private LocalDateTime interviewTime;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id")
    private Job job;
    
    public ApplicantDTO toDTO() {
        return new ApplicantDTO(this.getApplicantId(), this.getName(), this.getEmail(), this.getPhone(), this.getWebsite(),this.getResume()!=null?Base64.getEncoder().encodeToString(this.getResume()):null, this.getCoverLetter(),this.getTimestamp(), this.getApplicationStatus(), this.interviewTime);
    }
}
