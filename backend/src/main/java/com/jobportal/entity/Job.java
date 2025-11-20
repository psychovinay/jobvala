package com.jobportal.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.Lob;
import jakarta.persistence.Basic;
import jakarta.persistence.FetchType;
import jakarta.persistence.Column;

import com.jobportal.dto.JobDTO;
import com.jobportal.dto.JobStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String jobTitle;
    private String company;
    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Applicant> applicants;
    @Lob
    @Basic(fetch = FetchType.EAGER)
    @Column(columnDefinition = "LONGTEXT")
    private String about;
    private String experience;
    private String jobType;
    private String location;
    private Long packageOffered; 
    private LocalDateTime postTime;
    @Lob
    @Basic(fetch = FetchType.EAGER)
    @Column(columnDefinition = "LONGTEXT")
    private String description;
    @ElementCollection
    private List<String> skillsRequired;
    @Enumerated(EnumType.STRING)
    private JobStatus jobStatus;
    private Long postedBy;
    
    public JobDTO toDTO() {
        return new JobDTO(this.id, this.jobTitle, this.company,this.applicants!=null?this.applicants.stream().map((x)->x.toDTO()).toList():null,this.about, this.experience, this.jobType, this.location, this.packageOffered, this.postTime, this.description, this.skillsRequired, this.jobStatus, this.postedBy);
    }
}
