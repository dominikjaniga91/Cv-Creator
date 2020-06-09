package com.cvgenerator.controller;

import com.cvgenerator.domain.dto.UserCvDto;
import com.cvgenerator.service.implementation.UserCvServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "User CV controller")
@RestController
@RequestMapping("/api")
public class UserCvController {


    private final UserCvServiceImpl userCvService;

    @Autowired
    public UserCvController(UserCvServiceImpl userCvService) {
        this.userCvService = userCvService;
    }

    @ApiOperation(value = "Get information about specific user's CV")
    @GetMapping("/cv/{id}")
    public ResponseEntity<UserCvDto> getCvById(@PathVariable Long id){

        return new ResponseEntity<>(userCvService.getUserCvById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Save new CV in database linked with user")
    @PostMapping("/cv/{userId}")
    public void saveUserCv(@PathVariable Long userId, @RequestBody UserCvDto userCvDto){
        userCvService.saveUserCv(userId, userCvDto);
    }

    @ApiOperation(value = "Delete CV from database using ID")
    @DeleteMapping("/cv/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUserCv(@PathVariable Long id){
        userCvService.deleteCvById(id);
    }


}
