package com.cvgenerator.service;

import com.cvgenerator.domain.entity.Course;

public interface CourseService {

    void createCourse(Long userCvId, Course course);

    void updateCourse(Course course);

    void deleteCourseById(Long id);
}
