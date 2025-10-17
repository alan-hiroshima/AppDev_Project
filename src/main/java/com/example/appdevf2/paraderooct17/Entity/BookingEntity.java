package com.example.appdevf2.paraderooct17.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
@Table(name = "tbl_booking")
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private int bookingId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "Usersid")
    @JsonBackReference("user-bookings")
    private UserEntity user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "subject_id", referencedColumnName = "subject_id")
    @JsonBackReference("subject-bookings")
    private SubjectEntity subject;

    @ManyToOne(optional = false)
    @JoinColumn(name = "tutor_profile_id", referencedColumnName = "tutor_profile_id")
    @JsonBackReference("tutor-bookings")
    private TutorProfileEntity tutorProfile;

    @OneToOne(mappedBy = "booking", cascade = CascadeType.ALL)
    @JsonManagedReference("booking-review")
    private ReviewEntity review;

    @Column(name = "booking_date")
    private String bookingDate;

    @Column(name = "status")
    private String status;

    @Column(name = "remarks")
    private String remarks;

    public BookingEntity() {
    }

    public BookingEntity(int bookingId, String bookingDate, String status, String remarks) {
        this.bookingId = bookingId;
        this.bookingDate = bookingDate;
        this.status = status;
        this.remarks = remarks;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public SubjectEntity getSubject() {
        return subject;
    }

    public void setSubject(SubjectEntity subject) {
        this.subject = subject;
    }

    public TutorProfileEntity getTutorProfile() {
        return tutorProfile;
    }

    public void setTutorProfile(TutorProfileEntity tutorProfile) {
        this.tutorProfile = tutorProfile;
    }

    public ReviewEntity getReview() {
        return review;
    }

    public void setReview(ReviewEntity review) {
        this.review = review;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
