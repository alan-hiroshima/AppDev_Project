package com.example.appdevf2.paraderooct17.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.appdevf2.paraderooct17.Service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.appdevf2.paraderooct17.Entity.UserEntity;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;


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

    @GetMapping
    public List<UserEntity> getAllUsers() {
        return userService.getAllUsers();
    }
    
}
