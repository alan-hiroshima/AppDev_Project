package com.example.appdevf2.paraderooct17.Entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tbl_dispute")
public class DisputeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int disputeId;

    @ManyToOne
    @JoinColumn(name = "booking_id", nullable = false)
    @JsonIgnoreProperties({"user", "tutorProfile", "subject", "review"}) 
    private BookingEntity booking;

    @Column(name = "reason")
    private String reason; // e.g., "Tutor didn't show up", "Rude behavior"

    @Column(name = "status")
    private String status; // "PENDING", "RESOLVED", "DISMISSED"

    @Column(name = "created_at")
    private String createdAt;

    public DisputeEntity() {
    }

    public DisputeEntity(BookingEntity booking, String reason, String status, String createdAt) {
        this.booking = booking;
        this.reason = reason;
        this.status = status;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public int getDisputeId() { return disputeId; }
    public void setDisputeId(int disputeId) { this.disputeId = disputeId; }

    public BookingEntity getBooking() { return booking; }
    public void setBooking(BookingEntity booking) { this.booking = booking; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
}