package com.cvgenerator.controller;

import com.cvgenerator.domain.entity.Project;
import com.cvgenerator.service.implementation.ProjectServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Project controller")
@RestController
@RequestMapping("/api/cv/project")
public class ProjectController {

    private final ProjectServiceImpl projectService;

    @Autowired
    public ProjectController(ProjectServiceImpl projectService) {
        this.projectService = projectService;
    }

    @ApiOperation(value = "Save new project for cv with specific ID into database ")
    @PostMapping("/{cvId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createProject(@PathVariable Long cvId, @RequestBody Project project){
        projectService.createProject(cvId, project);
    }

    @ApiOperation(value = "Updates details about user project")
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateProject(@RequestBody Project project){
        projectService.updateProject(project);
    }

    @ApiOperation(value = "Delete user project from database")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProject(@PathVariable Long id){
        projectService.deleteProject(id);
    }
}
