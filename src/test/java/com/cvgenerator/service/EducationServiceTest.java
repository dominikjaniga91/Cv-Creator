package com.cvgenerator.service;

import com.cvgenerator.domain.dto.UserCvDto;
import com.cvgenerator.domain.dto.UserDto;
import com.cvgenerator.domain.entity.Education;
import com.cvgenerator.domain.entity.User;
import com.cvgenerator.exceptions.notfound.EducationNotFoundException;
import com.cvgenerator.repository.EducationRepository;
import com.cvgenerator.service.implementation.EducationServiceImpl;
import com.cvgenerator.service.implementation.UserCvServiceImpl;
import com.cvgenerator.service.implementation.UserServiceImpl;
import com.cvgenerator.utils.service.implementation.MailServiceImpl;
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
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class EducationServiceTest {

    @Autowired private EducationServiceImpl educationService;
    @Autowired private EducationRepository repository;
    @Autowired private UserServiceImpl userService;
    @Autowired private UserCvServiceImpl userCvService;
    @MockBean private MailServiceImpl mailService;
    private Education education;

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

        education = Education.builder()
                .id(1L)
                .city("Tarnów")
                .school("IV High school")
                .build();

        userService.saveUser(userDto);
        userCvService.saveUserCv(1L, userCvDto);
        educationService.createEducation(1L, education);
    }

    @Test
    @DisplayName("Should return education school from database")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void shouldReturnEducationSchoolFromDatabase(){
        Education foundedEducation = repository.findById(1L).orElseThrow();
        assertEquals("IV High school", foundedEducation.getSchool());
    }

    @Test
    @DisplayName("Should return new education school after update")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void shouldReturnNewEducationSchoolAfterUpdate(){

        String expected = "V High school";
        education.setSchool(expected);
        educationService.updateEducation(education);
        Education foundedEducation = repository.findById(1L).orElseThrow();
        assertEquals(expected, foundedEducation.getSchool());
    }

    @Test
    @DisplayName("Should thrown an exception after try to delete non existing education ")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void shouldThrownAnException_afterTryToDeleteNonExistingEducation(){

        EducationNotFoundException exception = assertThrows(EducationNotFoundException.class, () -> educationService.deleteEducationById(10L));
        assertEquals("Education does not exist", exception.getMessage());
    }
}


