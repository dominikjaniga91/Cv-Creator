package com.cvgenerator.service.implementation;

import com.cvgenerator.domain.entity.Course;
import com.cvgenerator.domain.entity.UserCv;
import com.cvgenerator.repository.CourseRepository;
import com.cvgenerator.repository.UserCvRepository;
import com.cvgenerator.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

    private final UserCvRepository userCvRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(UserCvRepository userCvRepository, CourseRepository courseRepository) {
        this.userCvRepository = userCvRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public void createCourse(Long userCvId, Course course) {
        UserCv userCv = userCvRepository.findById(userCvId).orElseThrow();
        course.setUserCv(userCv);
        courseRepository.save(course);
    }

    @Override
    public void updateCourse(Course course) {
        Long id = course.getId();
        Course foundedCourse = courseRepository.findById(id).orElseThrow();

        foundedCourse.setSchool(course.getSchool());
        foundedCourse.setCity(course.getCity());
        foundedCourse.setStartDate(course.getStartDate());
        foundedCourse.setFinishDate(course.getFinishDate());
        foundedCourse.setDescription(course.getDescription());
        courseRepository.save(foundedCourse);

    }
}
