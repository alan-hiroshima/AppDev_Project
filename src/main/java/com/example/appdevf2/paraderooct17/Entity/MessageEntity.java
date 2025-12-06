package com.example.appdevf2.paraderooct17.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_messages")
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int messageId;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    @JsonBackReference("booking-messages")
    private BookingEntity booking;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    @JsonIgnoreProperties({ "password", "bookings", "notifications", "profile", "tutorProfile", "role", "isActive",
            "isStaff", "dateJoined" })
    private UserEntity sender;

    @Column(nullable = false)
    private String text;

    @Column(name = "sent_at")
    private String sentAt;

    @Column(name = "read_at")
    private String readAt;

    public MessageEntity() {
    }

    public MessageEntity(BookingEntity booking, UserEntity sender, String text, String sentAt) {
        this.booking = booking;
        this.sender = sender;
        this.text = text;
        this.sentAt = sentAt;
        this.readAt = null;
    }

    // getters and setters
    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public BookingEntity getBooking() {
        return booking;
    }

    public void setBooking(BookingEntity booking) {
        this.booking = booking;
    }

    public UserEntity getSender() {
        return sender;
    }

    public void setSender(UserEntity sender) {
        this.sender = sender;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSentAt() {
        return sentAt;
    }

    public void setSentAt(String sentAt) {
        this.sentAt = sentAt;
    }

    public String getReadAt() {
        return readAt;
    }

    public void setReadAt(String readAt) {
        this.readAt = readAt;
    }

}
