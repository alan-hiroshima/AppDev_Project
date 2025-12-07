package com.example.appdevf2.paraderooct17.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_payment")
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private int paymentId;

    @OneToOne(optional = false)
    @JoinColumn(name = "booking_id", referencedColumnName = "booking_id")
    private BookingEntity booking;

    @Column(name = "amount")
    private float amount;

    // e.g. "PENDING", "SUCCEEDED", "FAILED", "REFUNDED"
    @Column(name = "status")
    private String status;

    // e.g. "CARD", "GCASH", "PAYPAL", etc.
    @Column(name = "method")
    private String method;

    // reference from payment gateway (or just some dummy text for now)
    @Column(name = "provider_ref")
    private String providerRef;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "updated_at")
    private String updatedAt;

    public PaymentEntity() {}

    public int getPaymentId() {
        return paymentId;
    }
    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public BookingEntity getBooking() {
        return booking;
    }
    public void setBooking(BookingEntity booking) {
        this.booking = booking;
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

    public String getMethod() {
        return method;
    }
    public void setMethod(String method) {
        this.method = method;
    }

    public String getProviderRef() {
        return providerRef;
    }
    public void setProviderRef(String providerRef) {
        this.providerRef = providerRef;
    }

    public String getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
