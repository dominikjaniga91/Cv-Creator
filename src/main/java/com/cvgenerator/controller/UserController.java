package com.cvgenerator.controller;

import com.cvgenerator.domain.entity.User;
import com.cvgenerator.service.implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    private UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUserAccount(@RequestBody User user){
        userService.saveUser(user);
    }

}
