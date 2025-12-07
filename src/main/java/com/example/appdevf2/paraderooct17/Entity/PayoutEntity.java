package com.example.appdevf2.paraderooct17.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_payout")
public class PayoutEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payout_id")
    private int payoutId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "tutor_profile_id", referencedColumnName = "tutor_profile_id")
    private TutorProfileEntity tutorProfile;

    @Column(name = "amount")
    private float amount;

    // e.g. "REQUESTED", "PROCESSING", "PAID", "FAILED"
    @Column(name = "status")
    private String status;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "processed_at")
    private String processedAt;

    public PayoutEntity() {}

    public int getPayoutId() {
        return payoutId;
    }
    public void setPayoutId(int payoutId) {
        this.payoutId = payoutId;
    }

    public TutorProfileEntity getTutorProfile() {
        return tutorProfile;
    }
    public void setTutorProfile(TutorProfileEntity tutorProfile) {
        this.tutorProfile = tutorProfile;
    }

    public float getAmount() {
        return amount;
    }
    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getProcessedAt() {
        return processedAt;
    }
    public void setProcessedAt(String processedAt) {
        this.processedAt = processedAt;
    }
}
