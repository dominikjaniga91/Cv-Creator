package com.cvgenerator.controller;

import com.cvgenerator.domain.entity.Education;
import com.cvgenerator.service.implementation.EducationServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Education controller")
@RestController
@RequestMapping("/api/cv/education")
public class EducationController {


    private final EducationServiceImpl educationService;

    @Autowired
    public EducationController(EducationServiceImpl educationService) {
        this.educationService = educationService;
    }

    @ApiOperation(value = "Save new education for cv with specific ID into database ")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{id}")
    public void createEducation(@PathVariable Long id, @RequestBody Education education) {
        educationService.createEducation(id, education);
    }

    @ApiOperation(value = "Updates details about education")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public void updateEducation(@RequestBody Education education) {
        educationService.updateEducation(education);
    }

    @ApiOperation(value = "Delete education from cv education")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void deleteEducation(@PathVariable Long id) {
        educationService.deleteEducation(id);
    }
}
