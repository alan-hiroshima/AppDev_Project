package com.example.appdevf2.paraderooct17.Service;

import org.springframework.stereotype.Service;
import com.example.appdevf2.paraderooct17.Entity.UserEntity;
import com.example.appdevf2.paraderooct17.Repository.UserRepository;
import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity saveUser(UserEntity user) {
        return userRepository.save(user);
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }
}
