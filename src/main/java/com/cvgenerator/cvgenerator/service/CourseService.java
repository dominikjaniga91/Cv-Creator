package com.cvgenerator.cvgenerator.service;

import com.cvgenerator.cvgenerator.domain.entity.Course;

public interface CourseService {

    void saveCourse(Long userCvId, Course course);
}
