package com.jobportal.entity;

import java.util.Base64;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Basic;
import jakarta.persistence.FetchType;
import jakarta.persistence.Column;

import com.jobportal.dto.Certification;
import com.jobportal.dto.Experience;
import com.jobportal.dto.ProfileDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "profiles")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String jobTitle;
    private String company;
    private String location;
    private String about;
    @Lob
    @Basic(fetch = FetchType.EAGER)
    @Column(columnDefinition = "LONGBLOB")
    private byte[] picture; 
    private Long totalExp;
    @ElementCollection
    private List<String> skills;
    @ElementCollection
    private List<Experience>experiences;
    @ElementCollection
    private List<Certification>certifications;
    @ElementCollection
    private List<Long>savedJobs;
    
    public ProfileDTO toDTO() {
        return new ProfileDTO(this.id, this.name, this.email, this.jobTitle, this.company, this.location, this.about, this.picture!=null?Base64.getEncoder().encodeToString(this.picture):null, this.totalExp, this.skills, this.experiences, this.certifications, this.savedJobs);
    }
}
