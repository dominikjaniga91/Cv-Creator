package com.cvgenerator.controller;

import com.cvgenerator.domain.entity.Experience;
import com.cvgenerator.service.implementation.ExperienceServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Experience controller")
@RestController
@RequestMapping("/api")
public class ExperienceController {

    private final ExperienceServiceImpl experienceService;

    @Autowired
    public ExperienceController(ExperienceServiceImpl experienceService) {
        this.experienceService = experienceService;
    }

    @ApiOperation(value = "Save new experience for cv with specific ID into database ")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/cv/experience/{id}")
    public void createEducation(@PathVariable Long id, @RequestBody Experience experience) {
        experienceService.createExperience(id, experience);
    }
}
