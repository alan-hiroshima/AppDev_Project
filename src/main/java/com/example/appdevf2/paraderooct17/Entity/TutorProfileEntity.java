package com.example.appdevf2.paraderooct17.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "tbl_tutorprofile")
public class TutorProfileEntity {

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "Usersid")
    @JsonBackReference
    private UserEntity user;

    @OneToMany(mappedBy = "tutorProfile", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<SubjectEntity> subjects;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tutor_profile_id")
    private int tutorProfileId;

    @Column(name = "hourly_rate")
    private float hourlyRate;

    @Column(name = "bio")
    private String bio;

    @Column(name = "rating_average")
    private float ratingAverage;

    @Column(name = "rating_count")
    private float ratingCount;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "updated_at")
    private String updatedAt;

    //reverse mapping for booking
    @OneToMany(mappedBy = "tutorProfile", cascade = CascadeType.ALL)
    @JsonManagedReference("tutor-bookings")
    private List<BookingEntity> bookings;

    //reverse mapping for review
    @OneToMany(mappedBy = "tutorProfile", cascade = CascadeType.ALL)
    @JsonBackReference("tutor-review")
    private List<ReviewEntity> reviews;

    public TutorProfileEntity() {
    }

    public TutorProfileEntity(int tutorProfileId, float hourlyRate, String bio, float ratingAverage,
            float ratingCount, boolean isActive, String createdAt, String updatedAt) {
        this.tutorProfileId = tutorProfileId;
        this.hourlyRate = hourlyRate;
        this.bio = bio;
        this.ratingAverage = ratingAverage;
        this.ratingCount = ratingCount;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getTutorProfileId() {
        return tutorProfileId;
    }

    public void setTutorProfileId(int tutorProfileId) {
        this.tutorProfileId = tutorProfileId;
    }

    public float getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(float hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public float getRatingAverage() {
        return ratingAverage;
    }

    public void setRatingAverage(float ratingAverage) {
        this.ratingAverage = ratingAverage;
    }

    public float getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(float ratingCount) {
        this.ratingCount = ratingCount;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
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

    //more more reverse mappings
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public List<SubjectEntity> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SubjectEntity> subjects) {
        this.subjects = subjects;
    }

    public List<BookingEntity> getBookings() {
        return bookings;
    }

    public void setBookings(List<BookingEntity> bookings) {
        this.bookings = bookings;
    }

    public List<ReviewEntity> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewEntity> reviews) {
        this.reviews = reviews;
    }
}
