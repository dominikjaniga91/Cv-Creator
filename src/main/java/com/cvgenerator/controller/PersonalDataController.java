package com.cvgenerator.controller;

import com.cvgenerator.domain.entity.PersonalData;
import com.cvgenerator.service.implementation.PersonalDataServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Personal data controller")
@RestController
@RequestMapping("/api/cv/personal-data")
public class PersonalDataController {

    private final PersonalDataServiceImpl personalDataService;

    public PersonalDataController(PersonalDataServiceImpl personalDataService) {
        this.personalDataService = personalDataService;
    }

    @ApiOperation(value = "Save new personal data for cv with specific ID into database")
    @PostMapping("/{cvId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createPersonalData(@PathVariable Long cvId, @RequestBody PersonalData personalData){
        personalDataService.createPersonalData(cvId, personalData);
    }

    @ApiOperation(value = "Update user personal data")
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updatePersonalData(@RequestBody PersonalData personalData){
        personalDataService.updatePersonalData(personalData);
    }

    @ApiOperation(value = "Delete user personal data from database")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePersonalData(@PathVariable Long id){
        personalDataService.deletePersonalDataById(id);
    }
}
