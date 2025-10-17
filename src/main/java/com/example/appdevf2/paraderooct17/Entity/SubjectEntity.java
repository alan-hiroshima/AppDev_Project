package com.example.appdevf2.paraderooct17.Entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;
import java.util.List;

@Entity
@Table(name = "tbl_subject")
public class SubjectEntity {

    @ManyToOne
    @JoinColumn(name = "tutor_profile_id")
    @JsonBackReference
    private TutorProfileEntity tutorProfile;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id")
    private int subjectId;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    //added the reverse mapping here as well
    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    @JsonManagedReference("subject-bookings")
    private List<BookingEntity> bookings;

    public SubjectEntity() {
    }

    public SubjectEntity(int subjectId, String name, String code) {
        this.subjectId = subjectId;
        this.name = name;
        this.code = code;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    // more reverse mapping
     public void setTutorProfile(TutorProfileEntity tutorProfile) {
        this.tutorProfile = tutorProfile;
    }

    public List<BookingEntity> getBookings() {
        return bookings;
    }

    public void setBookings(List<BookingEntity> bookings) {
        this.bookings = bookings;
    }
}
