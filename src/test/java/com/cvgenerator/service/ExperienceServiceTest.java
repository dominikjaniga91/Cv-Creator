package com.cvgenerator.service;

import com.cvgenerator.domain.dto.UserCvDto;
import com.cvgenerator.domain.dto.UserDto;
import com.cvgenerator.domain.entity.Experience;
import com.cvgenerator.domain.entity.User;
import com.cvgenerator.repository.ExperienceRepository;
import com.cvgenerator.service.implementation.ExperienceServiceImpl;
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
public class ExperienceServiceTest {

    @Autowired private ExperienceServiceImpl experienceService;
    @Autowired private ExperienceRepository repository;
    @Autowired private UserServiceImpl userService;
    @Autowired private UserCvServiceImpl userCvService;
    @MockBean private MailServiceImpl mailService;
    private Experience experience;

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

        experience = Experience.builder()
                .id(1L)
                .city("Tarnów")
                .company("LPP S.A.")
                .build();

        userService.saveUser(userDto);
        userCvService.saveUserCv(1L, userCvDto);
        experienceService.createExperience(1L, experience);
    }

    @Test
    @DisplayName("Should return experience company from database")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void shouldReturnExperienceCompanyFromDatabase(){
        Experience foundedExperience = repository.findById(1L).orElseThrow();
        assertEquals("LPP S.A.", foundedExperience.getCompany());
    }

    @Test
    @DisplayName("Should return new experience company after update")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void shouldReturnNewExperienceCompanyAfterUpdate(){

        String expected = "Jeronimo Martins Polska";
        experience.setCompany(expected);
        experienceService.updateExperience(experience);
        Experience foundedExperience = repository.findById(1L).orElseThrow();
        assertEquals(expected, foundedExperience.getCompany());
    }

}


