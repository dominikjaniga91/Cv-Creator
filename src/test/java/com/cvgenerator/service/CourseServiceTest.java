package com.cvgenerator.service;

import com.cvgenerator.domain.dto.UserCvDto;
import com.cvgenerator.domain.dto.UserDto;
import com.cvgenerator.domain.entity.Course;
import com.cvgenerator.domain.entity.User;
import com.cvgenerator.exceptions.notfound.CourseNotFoundException;
import com.cvgenerator.repository.CourseRepository;
import com.cvgenerator.service.implementation.CourseServiceImpl;
import com.cvgenerator.service.implementation.UserCvServiceImpl;
import com.cvgenerator.service.implementation.UserServiceImpl;
import com.cvgenerator.utils.service.implementation.MailServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class CourseServiceTest {

    @Autowired private CourseServiceImpl courseService;
    @Autowired private CourseRepository repository;
    @Autowired private UserServiceImpl userService;
    @Autowired private UserCvServiceImpl userCvService;
    @MockBean private MailServiceImpl mailService;
    private Course course;

    @BeforeEach
    void setUp() {
        BDDMockito.doNothing().when(mailService).sendConfirmationEmail(ArgumentMatchers.any(User.class));

        UserCvDto userCvDto = new UserCvDto.UserCvDtoBuilder()
                .setId(1L)
                .setName("Dominik cv")
                .setTemplateName("Aquarius")
                .buildUserCvDto();

        UserDto userDto = new UserDto.UserDtoBuilder()
                .setId(1L)
                .setFirstName("Dominik")
                .setLastName("Janiga")
                .setEmail("dominikjaniga91@gmail.com")
                .setPassword("dominik123")
                .setRole("USER")
                .setActive(true)
                .buildUserDto();

        course = Course.builder()
                .id(1L)
                .city("Tarnów")
                .school("Kodilla")
                .build();

        userService.saveUser(userDto);
        userCvService.saveUserCv(1L, userCvDto);
        courseService.createCourse(1L, course);
    }

    @Test
    @DisplayName("Should return course school from database")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void shouldReturnCourseSchoolFromDB(){

        Course foundedCourse = repository.findById(1L).orElseThrow();
        Assertions.assertEquals("Kodilla", foundedCourse.getSchool());
    }

    @Test
    @DisplayName("Should return new course school after update")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void shouldReturnNewCourseSchoolAfterUpdate(){

        String expected = "SD Academy";
        course.setSchool(expected);
        courseService.updateCourse(course);
        Course foundedCourse = repository.findById(1L).orElseThrow();
        Assertions.assertEquals(expected, foundedCourse.getSchool());
    }

    @Test
    @DisplayName("Should thrown an CourseNotFoundException")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void shouldThrownAnCourseNotFoundException(){
        CourseNotFoundException exception = Assertions.assertThrows(CourseNotFoundException.class, () -> courseService.deleteCourseById(10L));
        Assertions.assertEquals("Course does not exist", exception.getMessage());

    }
}
