package com.example.appdevf2.paraderooct17.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.appdevf2.paraderooct17.Entity.TutorProfileEntity;
import com.example.appdevf2.paraderooct17.Service.TutorProfileService;

@RestController
@RequestMapping("/tutorprofile")
public class TutorProfileController {

    private TutorProfileService tutorProfileService;

    public TutorProfileController(TutorProfileService tutorProfileService) {
        this.tutorProfileService = tutorProfileService;
    }

    @PostMapping
    public TutorProfileEntity createTutorProfile(@RequestBody TutorProfileEntity tutorProfile) {
        return tutorProfileService.saveTutorProfile(tutorProfile);
    }

    @GetMapping
    public List<TutorProfileEntity> getAllTutorProfiles() {
        return tutorProfileService.getAllTutorProfiles();
    }

    @PutMapping("/{id}")
    public TutorProfileEntity updateTutorProfile(@PathVariable int id, @RequestBody TutorProfileEntity tutorProfile) {
        return tutorProfileService.updateTutorProfile(id, tutorProfile);
    }

    @DeleteMapping("/{id}")
    public void deleteTutorProfile(@PathVariable int id) {
        tutorProfileService.deleteTutorProfile(id);
    }

}
