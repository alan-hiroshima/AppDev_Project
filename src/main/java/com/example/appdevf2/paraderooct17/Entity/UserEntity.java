package com.example.appdevf2.paraderooct17.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity {

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private TutorProfileEntity tutorProfile;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Usersid;

    @Column(name = "Email")
    private String email;

    @Column(name = "Password")
    private String password;

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "LastName")
    private String lastName;

    @Column(name = "DateJoined")
    private String dateJoined;

    @Column(name = "IsActive")
    private boolean isActive;

    @Column(name = "IsStaff")
    private boolean isStaff;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private ProfileEntity profile;

    // created reverse mapping for the Booking - Brent
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference("user-bookings")
    private List<BookingEntity> bookings;
 
    public UserEntity() {
    }

    public UserEntity(String email, String password, String firstName, String lastName, String dateJoined,
            boolean isActive, boolean isStaff) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateJoined = dateJoined;
        this.isActive = isActive;
        this.isStaff = isStaff;
    }
    
    public int getUsersid() {
        return Usersid;
    }
    public void setUsersid(int usersid) {
        Usersid = usersid;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getDateJoined() {
        return dateJoined;
    }
    public void setDateJoined(String dateJoined) {
        this.dateJoined = dateJoined;
    }
    public boolean getIsActive() {
        return isActive;
    }
    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
    public boolean getIsStaff() {
        return isStaff;
    }
    public void setIsStaff(boolean isStaff) {
        this.isStaff = isStaff;
    }
 
    // also added these cuz why not
    public TutorProfileEntity getTutorProfile() {
        return tutorProfile;
    }

    public void setTutorProfile(TutorProfileEntity tutorProfile) {
        this.tutorProfile = tutorProfile;
    }

    public ProfileEntity getProfile() {
        return profile;
    }

    public void setProfile(ProfileEntity profile) {
        this.profile = profile;
    }

    public List<BookingEntity> getBookings() {
        return bookings;
    }

    public void setBookings(List<BookingEntity> bookings) {
        this.bookings = bookings;
    }
}
