package com.jobportal.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import com.jobportal.dto.NotificationDTO;
import com.jobportal.dto.NotificationStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String message;
    private String action;
    private String route;
    @Enumerated(EnumType.STRING)
    private NotificationStatus status;
    private LocalDateTime timestamp;
    public NotificationDTO toDTO() {
        return new NotificationDTO (this.id, this.userId, this.message, this.action, this.route, this.status, this.timestamp);
    }
}
