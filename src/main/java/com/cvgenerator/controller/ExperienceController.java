package com.cvgenerator.controller;

import com.cvgenerator.domain.entity.Experience;
import com.cvgenerator.service.implementation.ExperienceServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"${settings.cors_origin}"})
@Api(tags = "Experience controller")
@RestController
@RequestMapping("/api/cv/experience")
public class ExperienceController {

    private final ExperienceServiceImpl experienceService;

    @Autowired
    public ExperienceController(ExperienceServiceImpl experienceService) {
        this.experienceService = experienceService;
    }

    @ApiOperation(value = "Save new experience for cv with specific ID into database ")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{cvId}")
    public void createEducation(@PathVariable Long cvId, @RequestBody Experience experience) {
        experienceService.createExperience(cvId, experience);
    }

    @ApiOperation(value = "Updates details about experience")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public void updateEducation(@RequestBody Experience experience) {
        experienceService.updateExperience(experience);
    }

    @ApiOperation(value = "Delete education from cv experience")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void deleteEducation(@PathVariable Long id) {
        experienceService.deleteExperienceById(id);
    }
}
