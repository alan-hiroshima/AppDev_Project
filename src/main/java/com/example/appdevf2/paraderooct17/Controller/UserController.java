package com.example.appdevf2.paraderooct17.Controller;
import org.springframework.web.bind.annotation.*;

import com.example.appdevf2.paraderooct17.Service.UserService;
import com.example.appdevf2.paraderooct17.Entity.UserEntity;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @PostMapping
    public UserEntity createUser(@RequestBody UserEntity user) {
        return userService.saveUser(user);
    }

    //getting all of the user
    @GetMapping
    public List<UserEntity> getAllUsers() {
        return userService.getAllUsers();
    }
    //getting the user by id
    @GetMapping("/{id}")
    public UserEntity getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @PostMapping("/login")
    public UserEntity login(@RequestBody Map<String, String> loginData) {
        String email = loginData.get("email");
        String password = loginData.get("password");

        // Use the service to check credentials
        UserEntity user = userService.authenticate(email, password);

        if (user != null) {
            return user;
        } else {
            // Throw an error if login fails (Frontend will receive 500 or 401)
            throw new RuntimeException("Invalid email or password");
        }
    }

    @PutMapping("/{id}")
    public UserEntity updateUser(@PathVariable int id, @RequestBody UserEntity userDetails) {
        // We delegate ALL logic to the Service. 
        // The Service will handle finding the ID, hashing passwords, and saving.
        UserEntity updatedUser = userService.updateUser(id, userDetails);
        
        if (updatedUser != null) {
            return updatedUser;
        }
        return null; 
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }

}
