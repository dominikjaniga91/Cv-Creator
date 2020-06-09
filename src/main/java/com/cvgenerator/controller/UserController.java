package com.cvgenerator.controller;

import com.cvgenerator.domain.dto.UserDto;
import com.cvgenerator.domain.entity.User;
import com.cvgenerator.service.implementation.UserServiceImpl;
import com.cvgenerator.utils.service.implementation.MailServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "User controller")
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserServiceImpl userService;


    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "Get information about user")
    @GetMapping("/user/{userId}")
    public ResponseEntity<UserDto> getUserInformation(@PathVariable Long userId){
         return new ResponseEntity<>(userService.findUserById(userId), HttpStatus.OK);
    }

    /**
     * @return Optional<List<UserCv>>
     */
    @ApiOperation(value = "Get list of user cv. List contains basic information about CV like: id, cv name, and template name")
    @GetMapping("/user/resume/{id}")
    public ResponseEntity<?> getListOfUserCv(@PathVariable Long id){
        return new ResponseEntity<>(userService.getListOfUserCv(id), HttpStatus.OK);
    }

    @ApiOperation(value ="Creates new user account")
    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUserAccount(@RequestBody UserDto userDto){
        userService.saveUser(userDto);

    }

    @ApiOperation(value ="Updates information about user account")
    @PutMapping("/user")
    public void updateUserAccount(@RequestBody UserDto userDto){
        userService.updateUser(userDto);
    }

    @ApiOperation(value ="Delete user account with all resumes")
    @DeleteMapping("/user/{userId}")
    public void deleteUserAccount(@PathVariable Long userId,
                                  @RequestParam String password){

        userService.deleteUserAccount(userId, password);
    }

}
