package com.cvgenerator.controller;

import com.cvgenerator.domain.dto.UserDto;
import com.cvgenerator.domain.entity.User;
import com.cvgenerator.service.implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    private UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserDto> getUserInformation(@PathVariable Long userId){
         return new ResponseEntity<>(userService.findUserById(userId), HttpStatus.OK);
    }

    /**
     * @return Optional<List<UserCv>>
     */

    @GetMapping("/user/resume/{id}")
    public ResponseEntity<?> getListOfUserCv(@PathVariable Long id){

        return new ResponseEntity<>(userService.getListOfUserCv(id), HttpStatus.OK);
    }

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUserAccount(@RequestBody User user){
        userService.saveUser(user);
    }

    @PutMapping("/user")
    public void updateUserAccount(@RequestBody User user){
        userService.updateUser(user);
    }

    @DeleteMapping("/user/{userId}")
    public void deleteUserAccount(@PathVariable Long userId,
                                  @RequestParam String password){

        userService.deleteUserAccount(userId, password);
    }

}
