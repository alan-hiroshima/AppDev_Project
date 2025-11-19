package com.example.appdevf2.paraderooct17.Service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.appdevf2.paraderooct17.Repository.UserRepository;
import com.example.appdevf2.paraderooct17.Entity.UserEntity;
import org.springframework.transaction.annotation.Transactional;
import com.example.appdevf2.paraderooct17.Entity.ProfileEntity;
import com.example.appdevf2.paraderooct17.Repository.ProfileRepository;
import java.util.List;


@Service
public class ProfileService {
    
    @Autowired
    private UserRepository userRepository;
    private  ProfileRepository profileRepository;
    

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }
    public ProfileEntity getProfileById(int id) {
        return profileRepository.findById(id).orElse(null);
    }

    // public ProfileEntity saveProfile(ProfileEntity profile) {
    //     return profileRepository.save(profile);
    // }

    public List<ProfileEntity> getAllProfiles() {
        return profileRepository.findAll();
    }

    @Transactional
    public ProfileEntity saveProfile(ProfileEntity profile) {
            // attach profile back to the user, if included
            if (profile.getUser() != null) {
              profile.getUser().setProfile(profile);
         }
        return profileRepository.save(profile);
    }

    @Transactional
    public ProfileEntity updateProfile(int id, ProfileEntity details) {

        ProfileEntity existing = profileRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Profile not found with id: " + id));

        // Update simple fields
        existing.setSchool(details.getSchool());
        existing.setProgram(details.getProgram());
        existing.setYearLevel(details.getYearLevel());
        existing.setPhone(details.getPhone());
        existing.setPhotoURL(details.getPhotoURL());
        existing.setVerified(details.isVerified());
        existing.setCreatedAt(details.getCreatedAt());
        existing.setUpdatedAt(details.getUpdatedAt());

        // Update User reference if provided
        if (details.getUser() != null) {
            int userId = details.getUser().getUsersid();
            UserEntity managedUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

            existing.setUser(managedUser);
        }

        return profileRepository.save(existing);
    }

    @Transactional
    public void deleteProfile(int id) {
        profileRepository.deleteById(id);
    }
}
