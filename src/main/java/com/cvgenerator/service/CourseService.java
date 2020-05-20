package com.cvgenerator.service;

import com.cvgenerator.domain.entity.Course;

public interface CourseService {

    void saveCourse(Long userCvId, Course course);
}
