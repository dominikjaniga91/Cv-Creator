package com.cvgenerator.controller;

import com.cvgenerator.domain.entity.Skill;
import com.cvgenerator.service.implementation.SkillServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Skill controller")
@RestController
@RequestMapping("/api/cv/skill")
public class SkillController {

    private final SkillServiceImpl skillService;

    public SkillController(SkillServiceImpl skillService) {
        this.skillService = skillService;
    }

    @ApiOperation(value = "Save new skill for cv with specific ID into database ")
    @PostMapping("/{cvId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createSkill(@PathVariable Long cvId, @RequestBody Skill skill){
        skillService.saveSkill(cvId, skill);
    }

}
