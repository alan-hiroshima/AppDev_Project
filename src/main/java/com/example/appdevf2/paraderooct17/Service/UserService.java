package com.example.appdevf2.paraderooct17.Service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.example.appdevf2.paraderooct17.Entity.UserEntity;
import com.example.appdevf2.paraderooct17.Repository.UserRepository;
import com.example.appdevf2.paraderooct17.Entity.ProfileEntity;
import com.example.appdevf2.paraderooct17.Entity.TutorProfileEntity;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public UserEntity saveUser(UserEntity user) {

        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedPassword);
        
        if (user.getRole() == null || user.getRole().isEmpty()) {
            if (user.getIsStaff()) {
                user.setRole("TUTOR");
            } else {
                user.setRole("STUDENT");
            }
        }

        // If user has a profile, link it back to the user
        if (user.getProfile() != null) {
            user.getProfile().setUser(user);
        }

        // If user has a tutor profile, link it back too
        if (user.getTutorProfile() != null) {
            user.getTutorProfile().setUser(user);

            // === REQUIRED FIX: Link Subjects back to the Tutor ===
            if (user.getTutorProfile().getSubjects() != null) {
                for (com.example.appdevf2.paraderooct17.Entity.SubjectEntity sub : user.getTutorProfile()
                        .getSubjects()) {
                    sub.setTutorProfile(user.getTutorProfile());
                }
            }

            // === REQUIRED FIX: Link Availability back to the Tutor ===
            if (user.getTutorProfile().getAvailabilities() != null) {
                for (com.example.appdevf2.paraderooct17.Entity.AvailabilityEntity avail : user.getTutorProfile()
                        .getAvailabilities()) {
                    avail.setTutorProfile(user.getTutorProfile());
                }
            }
        }

        return userRepository.save(user);
    }

    public UserEntity authenticate(String email, String rawPassword) {
        // Step 1: Find the user by email
        UserEntity user = userRepository.findByEmail(email);

        // Step 2: Check if user exists AND if password matches
        if (user != null) {
            // BCrypt.checkpw(plainText, hashed) returns true if they match
            if (BCrypt.checkpw(rawPassword, user.getPassword())) {
                return user; // Login successful
            }
        }
        
        return null; // Login failed (User not found or Wrong password)
    }

    @Transactional
    public UserEntity updateUser(int id, UserEntity details) {

        UserEntity existing = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        // Update basic fields
        existing.setEmail(details.getEmail());
        existing.setFirstName(details.getFirstName());
        existing.setLastName(details.getLastName());
        existing.setDateJoined(details.getDateJoined());
        existing.setIsActive(details.getIsActive());
        existing.setIsStaff(details.getIsStaff());
        
        // --- THE FIX: Handle Password Hashing Here ---
        if (details.getPassword() != null && !details.getPassword().isEmpty()) {
            // Only hash if the user is actually changing the password
            String newHashedPass = BCrypt.hashpw(details.getPassword(), BCrypt.gensalt());
            existing.setPassword(newHashedPass);
        }
        // If password is null/empty, we keep the old 'existing.password' (which is already hashed)

        // Update Profile if provided
        if (details.getProfile() != null) {
            ProfileEntity newProfile = details.getProfile();
            newProfile.setUser(existing);
            existing.setProfile(newProfile);
        }

        // Update TutorProfile if provided
        if (details.getTutorProfile() != null) {
            TutorProfileEntity newTutorProfile = details.getTutorProfile();
            newTutorProfile.setUser(existing);
            existing.setTutorProfile(newTutorProfile);
        }

        // Call Repository DIRECTLY, do not go back to saveUser()
        return userRepository.save(existing);
    }

    @Transactional
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}
