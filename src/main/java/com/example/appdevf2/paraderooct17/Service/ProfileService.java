package com.example.appdevf2.paraderooct17.Service;

import org.springframework.stereotype.Service;

import com.example.appdevf2.paraderooct17.Entity.ProfileEntity;
import com.example.appdevf2.paraderooct17.Repository.ProfileRepository;
import java.util.List;

@Service
public class ProfileService {
    private  ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public ProfileEntity saveProfile(ProfileEntity profile) {
        return profileRepository.save(profile);
    }

    public List<ProfileEntity> getAllProfiles() {
        return profileRepository.findAll();
    }


}
