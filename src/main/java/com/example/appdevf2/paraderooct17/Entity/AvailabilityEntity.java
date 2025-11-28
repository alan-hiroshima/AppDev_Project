package com.example.appdevf2.paraderooct17.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "tbl_availability")
public class AvailabilityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "day_of_week") // e.g., "MONDAY", "TUESDAY"
    private String dayOfWeek;

    @Column(name = "start_time") // e.g., "09:00"
    private String startTime;

    @Column(name = "end_time") // e.g., "17:00"
    private String endTime;

    @ManyToOne
    @JoinColumn(name = "tutor_profile_id")
    @JsonBackReference // Prevents infinite recursion
    private TutorProfileEntity tutorProfile;

    public AvailabilityEntity() {}

    public AvailabilityEntity(String dayOfWeek, String startTime, String endTime) {
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getDayOfWeek() { return dayOfWeek; }
    public void setDayOfWeek(String dayOfWeek) { this.dayOfWeek = dayOfWeek; }

    public String getStartTime() { return startTime; }
    public void setStartTime(String startTime) { this.startTime = startTime; }

    public String getEndTime() { return endTime; }
    public void setEndTime(String endTime) { this.endTime = endTime; }

    public TutorProfileEntity getTutorProfile() { return tutorProfile; }
    public void setTutorProfile(TutorProfileEntity tutorProfile) { this.tutorProfile = tutorProfile; }
}