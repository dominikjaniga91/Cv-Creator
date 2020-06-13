package com.cvgenerator.controller;

import com.cvgenerator.domain.entity.Interest;
import com.cvgenerator.service.implementation.InterestServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "Interest Controller")
@RequestMapping("/api/cv/interest")
public class InterestController {

    private final InterestServiceImpl interestService;

    @Autowired
    public InterestController(InterestServiceImpl interestService) {
        this.interestService = interestService;
    }

    @ApiOperation(value = "Save new interest for cv with specific ID into database ")
    @PostMapping("/{cvId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createInterest(@PathVariable Long cvId, @RequestBody Interest interest){
        interestService.createInterest(cvId, interest);
    }
}
