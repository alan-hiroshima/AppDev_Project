package com.example.appdevf2.paraderooct17.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.appdevf2.paraderooct17.Service.ProfileService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.appdevf2.paraderooct17.Entity.ProfileEntity;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/profile")
public class ProfileController {
    private ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping
    public ProfileEntity createProfile(@RequestBody ProfileEntity profile) {
        return profileService.saveProfile(profile);
    }

    @GetMapping
    public List<ProfileEntity> getAllProfiles() {
        return profileService.getAllProfiles();
    }
    
    
    
    
}
