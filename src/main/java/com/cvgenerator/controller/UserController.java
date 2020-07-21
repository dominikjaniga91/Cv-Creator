package com.cvgenerator.controller;

import com.cvgenerator.domain.dto.UserDto;
import com.cvgenerator.service.implementation.UserServiceImpl;
import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"${settings.cors_origin}"})
@Api(tags = "User controller")
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserServiceImpl userService;


    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "Get information about user")
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserInformation(@PathVariable Long id){
         return new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);
    }

    /**
     * @return Optional<List<UserCv>>
     */
    @ApiOperation(value = "Get list of user cv. List contains basic information about CV like: id, cv name, and template name")
    @GetMapping("/cv/{id}")
    public ResponseEntity<?> getListOfUserCv(@PathVariable Long id){
        return new ResponseEntity<>(userService.getListOfUserCv(id), HttpStatus.OK);
    }

    @ApiOperation(value ="Creates new user account")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUserAccount(@RequestBody UserDto userDto){
        userService.saveUser(userDto);

    }

    @ApiOperation(value ="Updates information about user account")
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateUserAccount(@RequestBody UserDto userDto){
        userService.updateUser(userDto);
    }

    @ApiOperation(value ="Delete user account with all resumes")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUserAccount(@PathVariable Long id,
                                  @RequestBody JsonNode requestPassword) throws MissingServletRequestParameterException {

        userService.deleteUserAccount(id, requestPassword);
    }

}
