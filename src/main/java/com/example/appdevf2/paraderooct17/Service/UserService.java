package com.example.appdevf2.paraderooct17.Service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.example.appdevf2.paraderooct17.Entity.UserEntity;
import com.example.appdevf2.paraderooct17.Repository.UserRepository;
import com.example.appdevf2.paraderooct17.Entity.ProfileEntity;
import com.example.appdevf2.paraderooct17.Entity.TutorProfileEntity;
import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    // public UserEntity saveUser(UserEntity user) {
    // return userRepository.save(user);
    // }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public UserEntity saveUser(UserEntity user) {

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

    @Transactional
    public UserEntity updateUser(int id, UserEntity details) {

        UserEntity existing = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        // Update fields
        existing.setEmail(details.getEmail());
        existing.setPassword(details.getPassword());
        existing.setFirstName(details.getFirstName());
        existing.setLastName(details.getLastName());
        existing.setDateJoined(details.getDateJoined());
        existing.setIsActive(details.getIsActive());
        existing.setIsStaff(details.getIsStaff());

        // Update Profile if provided
        if (details.getProfile() != null) {
            ProfileEntity newProfile = details.getProfile();
            newProfile.setUser(existing); // maintain link
            existing.setProfile(newProfile);
        }

        // Update TutorProfile if provided
        if (details.getTutorProfile() != null) {
            TutorProfileEntity newTutorProfile = details.getTutorProfile();
            newTutorProfile.setUser(existing);
            existing.setTutorProfile(newTutorProfile);
        }

        return userRepository.save(existing);
    }

    @Transactional
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}
