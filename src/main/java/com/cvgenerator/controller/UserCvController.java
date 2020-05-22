package com.cvgenerator.controller;

import com.cvgenerator.domain.dto.UserCvDto;
import com.cvgenerator.service.implementation.UserCvServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
