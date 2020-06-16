package com.cvgenerator.service.implementation;

import com.cvgenerator.config.Messages;
import com.cvgenerator.domain.entity.Course;
import com.cvgenerator.domain.entity.UserCv;
import com.cvgenerator.exceptions.notfound.CourseNotFoundException;
import com.cvgenerator.exceptions.notfound.UserCvNotFoundException;
import com.cvgenerator.repository.CourseRepository;
import com.cvgenerator.repository.UserCvRepository;
import com.cvgenerator.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

    private final UserCvRepository userCvRepository;
    private final CourseRepository courseRepository;
    private final Messages messages;

    @Autowired
    public CourseServiceImpl(UserCvRepository userCvRepository,
                             CourseRepository courseRepository,
                             Messages messages) {
        this.userCvRepository = userCvRepository;
        this.courseRepository = courseRepository;
        this.messages = messages;
    }

    @Override
    public void createCourse(Long userCvId, Course course) {
        UserCv userCv = userCvRepository.findById(userCvId).orElseThrow(() -> new UserCvNotFoundException(messages.get("userCv.notfound")));
        course.setUserCv(userCv);
        courseRepository.save(course);
    }

    @Override
    public void updateCourse(Course course) {
        Long id = course.getId();
        Course foundedCourse = courseRepository.findById(id).orElseThrow(() -> new CourseNotFoundException(messages.get("course.notfound")));

        foundedCourse.setSchool(course.getSchool());
        foundedCourse.setCity(course.getCity());
        foundedCourse.setStartDate(course.getStartDate());
        foundedCourse.setFinishDate(course.getFinishDate());
        foundedCourse.setDescription(course.getDescription());
        courseRepository.save(foundedCourse);

    }

    @Override
    public void deleteCourse(Long id) {
        try {
            courseRepository.deleteById(id);
        }catch (Exception ex){
            throw new CourseNotFoundException(messages.get("course.notfound"));
        }
    }
}
