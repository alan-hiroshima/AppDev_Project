package com.example.appdevf2.paraderooct17.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "tbl_review")
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private int reviewId;

    @OneToOne(optional = false)
    @JoinColumn(name = "booking_id", referencedColumnName = "booking_id")
    @JsonBackReference("booking-review")
    private BookingEntity booking;

    @ManyToOne(optional = false)
    @JoinColumn(name = "tutor_profile_id", referencedColumnName = "tutor_profile_id")
    @JsonBackReference("tutor-review")
    private TutorProfileEntity tutorProfile;

    @Column(name = "rating")
    private int rating;

    @Column(name = "comment")
    private String comment;

    @Column(name = "created_at")
    private String createdAt;

    public ReviewEntity() {
    }

    public ReviewEntity(int reviewId, int rating, String comment, String createdAt) {
        this.reviewId = reviewId;
        this.rating = rating;
        this.comment = comment;
        this.createdAt = createdAt;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public BookingEntity getBooking() {
        return booking;
    }

    public void setBooking(BookingEntity booking) {
        this.booking = booking;
    }

    public TutorProfileEntity getTutorProfile() {
        return tutorProfile;
    }

    public void setTutorProfile(TutorProfileEntity tutorProfile) {
        this.tutorProfile = tutorProfile;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
