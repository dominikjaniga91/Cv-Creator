package com.cvgenerator.cvgenerator.service.implementation;

import com.cvgenerator.cvgenerator.domain.entity.Course;
import com.cvgenerator.cvgenerator.domain.entity.UserCv;
import com.cvgenerator.cvgenerator.repository.CourseRepository;
import com.cvgenerator.cvgenerator.repository.UserCvRepository;
import com.cvgenerator.cvgenerator.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

    private UserCvRepository userCvRepository;
    private CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(UserCvRepository userCvRepository, CourseRepository courseRepository) {
        this.userCvRepository = userCvRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public void saveCourse(Long userCvId, Course course) {
        UserCv userCv = userCvRepository.findById(userCvId).orElseThrow();
        course.setUserCv(userCv);
        courseRepository.save(course);
    }
}
