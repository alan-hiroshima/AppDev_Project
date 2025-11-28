package com.example.appdevf2.paraderooct17.Service;
    
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.appdevf2.paraderooct17.Entity.AvailabilityEntity;
import com.example.appdevf2.paraderooct17.Entity.TutorProfileEntity;
import com.example.appdevf2.paraderooct17.Repository.TutorProfileRepository;

@Service
public class TutorProfileService {

     private TutorProfileRepository tutorProfileRepository;

    public TutorProfileService(TutorProfileRepository tutorProfileRepository) {
        this.tutorProfileRepository = tutorProfileRepository;
    }

    public TutorProfileEntity saveTutorProfile(TutorProfileEntity tutorProfile) {
        tutorProfile.setCreatedAt(LocalDateTime.now().toString());
        tutorProfile.setUpdatedAt(LocalDateTime.now().toString());

        // === ADDED LOGIC ===
        // Link the children (Availability) to the parent (TutorProfile)
        if (tutorProfile.getAvailabilities() != null) {
            for (AvailabilityEntity availability : tutorProfile.getAvailabilities()) {
                availability.setTutorProfile(tutorProfile);
            }
        }
        // ===================

        return tutorProfileRepository.save(tutorProfile);
    }

    public List<TutorProfileEntity> getAllTutorProfiles() {
        return tutorProfileRepository.findAll();
    }

    // MODIFY LATER THE GET
    public TutorProfileEntity updateTutorProfile(int id, TutorProfileEntity tutorProfile) {
        TutorProfileEntity existingTutorProfile = tutorProfileRepository.findById(id).orElseThrow(() -> new RuntimeException("Tutor Profile not found"));

        existingTutorProfile.setBio(tutorProfile.getBio());
        existingTutorProfile.setHourlyRate(tutorProfile.getHourlyRate());
        existingTutorProfile.setIsActive(tutorProfile.getIsActive());
        existingTutorProfile.setRatingAverage(tutorProfile.getRatingAverage());
        existingTutorProfile.setRatingCount(tutorProfile.getRatingCount());
        existingTutorProfile.setUpdatedAt(LocalDateTime.now().toString());

        // Update subjects
        if (tutorProfile.getSubjects() != null) {
            existingTutorProfile.setSubjects(tutorProfile.getSubjects());
        }

        // === ADDED LOGIC: Update Availability ===
        if (tutorProfile.getAvailabilities() != null) {
            // 1. Clear existing to trigger orphan removal (optional, but good for clean updates)
            if (existingTutorProfile.getAvailabilities() != null) {
                existingTutorProfile.getAvailabilities().clear();
            }
            
            // 2. Add new items and link them
            for (AvailabilityEntity availability : tutorProfile.getAvailabilities()) {
                availability.setTutorProfile(existingTutorProfile);
                existingTutorProfile.getAvailabilities().add(availability);
            }
        }
        // ========================================

        return tutorProfileRepository.save(existingTutorProfile);
    }

    public void deleteTutorProfile(int id) {
        tutorProfileRepository.deleteById(id);
        
    }

    
}
