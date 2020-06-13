package com.cvgenerator.controller;

import com.cvgenerator.domain.entity.Course;
import com.cvgenerator.service.implementation.CourseServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Course controller")
@RestController
@RequestMapping("/api/cv")
public class CourseController {

    private final CourseServiceImpl courseService;

    public CourseController(CourseServiceImpl courseService) {
        this.courseService = courseService;
    }

    @ApiOperation(value = "Save new course for cv with specific ID into database ")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/course/{id}")
    public void createCourse(@PathVariable Long id, @RequestBody Course course) {
        courseService.createCourse(id, course);
    }

    @ApiOperation(value = "Updates details about user courses")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/course")
    public void updateEducation(@RequestBody Course course) {
        courseService.updateCourse(course);
    }

    @ApiOperation(value = "Delete course from database")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/course/{id}")
    public void deleteEducation(@PathVariable Long id) {
        courseService.deleteCourse(id);
    }
}
