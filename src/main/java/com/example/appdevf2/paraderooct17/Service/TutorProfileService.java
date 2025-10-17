package com.example.appdevf2.paraderooct17.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.appdevf2.paraderooct17.Entity.TutorProfileEntity;
import com.example.appdevf2.paraderooct17.Repository.TutorProfileRepository;

@Service
public class TutorProfileService {

     private TutorProfileRepository tutorProfileRepository;

    public TutorProfileService(TutorProfileRepository tutorProfileRepository) {
        this.tutorProfileRepository = tutorProfileRepository;
    }

    public TutorProfileEntity saveTutorProfile(TutorProfileEntity tutorProfile) {
        return tutorProfileRepository.save(tutorProfile);
    }

    public List<TutorProfileEntity> getAllTutorProfiles() {
        return tutorProfileRepository.findAll();
    }
}
