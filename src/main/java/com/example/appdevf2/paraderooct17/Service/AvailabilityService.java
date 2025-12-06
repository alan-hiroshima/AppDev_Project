package com.example.appdevf2.paraderooct17.Service;

import com.example.appdevf2.paraderooct17.Entity.AvailabilityEntity;
import com.example.appdevf2.paraderooct17.Entity.TutorProfileEntity;
import com.example.appdevf2.paraderooct17.Repository.AvailabilityRepository;
import com.example.appdevf2.paraderooct17.Repository.TutorProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AvailabilityService {

    @Autowired
    private AvailabilityRepository availabilityRepository;

    @Autowired
    private TutorProfileRepository tutorProfileRepository;

    public List<AvailabilityEntity> getAllAvailabilities() {
        return availabilityRepository.findAll();
    }

    public AvailabilityEntity getAvailabilityById(int id) {
        return availabilityRepository.findById(id).orElse(null);
    }
    //comments usefule for fronted to know how to link availability to tutor
    @Transactional
    public AvailabilityEntity saveAvailability(AvailabilityEntity availability) {
        // get the Tutor ID from the requested Availability
        int tutorId = availability.getTutorProfile().getTutorProfileId();

        //get real TutorProfileEntity from the DB
        TutorProfileEntity tutor = tutorProfileRepository.findById(tutorId)
                .orElseThrow(() -> new RuntimeException("Tutor not found with id: " + tutorId));

        // set the TutorProfileEntity to the Availability
        availability.setTutorProfile(tutor);

        return availabilityRepository.save(availability);
    }

    @Transactional
    public AvailabilityEntity updateAvailability(int id, AvailabilityEntity details) {
        AvailabilityEntity existing = availabilityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Availability not found with id: " + id));

        // Update fields
        existing.setDayOfWeek(details.getDayOfWeek());
        existing.setStartTime(details.getStartTime());
        existing.setEndTime(details.getEndTime());

        // Update Tutor if changed
        if (details.getTutorProfile() != null) {
            int tutorId = details.getTutorProfile().getTutorProfileId();
            TutorProfileEntity tutor = tutorProfileRepository.findById(tutorId)
                    .orElseThrow(() -> new RuntimeException("Tutor not found"));
            existing.setTutorProfile(tutor);
        }

        return availabilityRepository.save(existing);
    }

    @Transactional
    public void deleteAvailability(int id) {
        availabilityRepository.deleteById(id);
    }
}