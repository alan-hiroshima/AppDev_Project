package com.example.appdevf2.paraderooct17.Entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "profiles")
public class ProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Profilesuuid;

    @Column(name = "School")
    private String school;

    @Column(name = "Program")
    private String program;

    @Column(name = "YearLevel")
    private String yearLevel;

    @Column(name = "Phone")
    private String phone;

    @Column(name = "PhotoURL")
    private String photoURL;

    @Column(name = "isVerified")
    private boolean isVerified;

    @Column(name = "CreatedAt")
    private String createdAt;

    @Column(name = "UpdatedAt")
    private String updatedAt;

     @OneToOne
     @JoinColumn(name = "user_id", referencedColumnName = "Usersid")
     @JsonBackReference
     private UserEntity user;
  
    public ProfileEntity() {
    }
    
    public ProfileEntity(String school, String program, String yearLevel, String phone, String photoURL,
            boolean isVerified, String createdAt, String updatedAt) {
        this.school = school;
        this.program = program;
        this.yearLevel = yearLevel;
        this.phone = phone;
        this.photoURL = photoURL;
        this.isVerified = isVerified;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    // Getters and Setters
    public int getProfilesuuid() {
        return Profilesuuid;
         }
    public void setProfilesuuid(int profilesuuid) {
        Profilesuuid = profilesuuid;
    }
    public String getSchool() {
        return school;
    }
    public void setSchool(String school) {
        this.school = school;
    }
    public String getProgram() {
        return program;
    }
    public void setProgram(String program) {
        this.program = program;
    }
    public String getYearLevel() {
        return yearLevel;
    }
    public void setYearLevel(String yearLevel) {
        this.yearLevel = yearLevel;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getPhotoURL() {
        return photoURL;
    }
    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;  
    }
    public boolean isVerified() {
        return isVerified;
    }
    public void setVerified(boolean isVerified) {
        this.isVerified = isVerified;
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
