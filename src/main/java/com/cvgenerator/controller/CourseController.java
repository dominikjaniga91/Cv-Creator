package com.cvgenerator.controller;


import com.cvgenerator.domain.entity.Course;
import com.cvgenerator.domain.entity.Education;
import com.cvgenerator.service.implementation.CourseServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Course controller")
@RestController
@RequestMapping("/api")
public class CourseController {

    private final CourseServiceImpl courseService;

    public CourseController(CourseServiceImpl courseService) {
        this.courseService = courseService;
    }

    @ApiOperation(value = "Save new education for cv with specific ID into database ")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/cv/education/{id}")
    public void createCourse(@PathVariable Long id, @RequestBody Course course) {
        courseService.createCourse(id, course);
    }
}
