package com.example.appdevf2.paraderooct17.Entity;


import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tble_notifications")
public class NotificationEntity {
    
    public enum NotificationType {
        NEW_BOOKING,
        BOOKING_CANCELLED,
        PAYOUT_PROCESSED,
        DISPUTE_RESOLVED,
        NEW_MESSAGE
    }  

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int notificationId;

    @Enumerated(EnumType.STRING)
    private NotificationType type;
    
    private String title;
    private String body;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "read_at")
    private String readAt;

    // User receiving the notification
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference("user-notifications")
    private UserEntity user;

    public NotificationEntity() {}

    public NotificationEntity( NotificationType type, String title, String body, String createdAt) {
        this.type = type;
        this.title = title;
        this.body = body;
        this.createdAt = createdAt;
        this.readAt = null;
    }


    // getter and setter methods
    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getReadAt() {
        return readAt;
    }

    public void setReadAt(String readAt) {
        this.readAt = readAt;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
    
    

}
