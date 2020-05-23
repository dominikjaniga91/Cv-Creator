package com.cvgenerator.controller;

import com.cvgenerator.domain.dto.UserCvDto;
import com.cvgenerator.service.implementation.UserCvServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserCvController {


    private UserCvServiceImpl userCvService;

    @Autowired
    public UserCvController(UserCvServiceImpl userCvService) {
        this.userCvService = userCvService;
    }

    @GetMapping("/cv/{id}")
    public ResponseEntity<UserCvDto> getCvById(@PathVariable Long id){

        return new ResponseEntity<>(userCvService.getUserCvById(id), HttpStatus.OK);
    }

    @PostMapping("/cv/{userId}")
    public void saveUserCv(@PathVariable Long userId, @RequestBody UserCvDto userCvDto){
        userCvService.saveUserCv(userId, userCvDto);
    }



}
