package com.cvgenerator.service;


import com.cvgenerator.domain.dto.UserCvDto;
import com.cvgenerator.domain.dto.UserDto;
import com.cvgenerator.domain.entity.Summary;
import com.cvgenerator.domain.entity.User;
import com.cvgenerator.exceptions.notfound.SummaryNotFoundException;
import com.cvgenerator.repository.SummaryRepository;
import com.cvgenerator.service.implementation.SummaryServiceImpl;
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

@ActiveProfiles("test")
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class SummaryServiceTest {

    @Autowired private SummaryServiceImpl summaryService;
    @Autowired private UserCvServiceImpl userCvService;
    @Autowired private UserServiceImpl userService;
    @Autowired private SummaryRepository repository;
    @MockBean private MailServiceImpl mailService;
    private Summary summary;


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

        summary = Summary.builder()
                .id(1L)
                .value("Lorem ipsum dolor sit amet.")
                .build();


        userService.saveUser(userDto);
        userCvService.saveUserCv(1L, userCvDto);
        summaryService.createSummary(1L, summary);
    }

    @Test
    @DisplayName("Should return summary value from database")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void shouldReturnSummaryValueFromDatabase(){


        Assertions.assertEquals("Lorem ipsum dolor sit amet.", summary.getValue());
    }

    @Test
    @DisplayName("Should return new summary value after update")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void shouldReturnNewSummaryValueAfterUpdate(){

        String expected = "There are many ways you can map a one-to-one relationship with Hibernate";
        summary.setValue(expected);
        summaryService.updateSummary(summary);
        Summary foundedSummary = repository.findById(1L).orElseThrow();
        Assertions.assertEquals(expected, foundedSummary.getValue());
    }

    @Test
    @DisplayName("Should thrown an SummaryNotFoundException after get non existing summary")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void shouldThrownAnException_afterDeleteFromDatabase(){
        SummaryNotFoundException exception = Assertions.assertThrows(SummaryNotFoundException.class, () -> summaryService.deleteSummaryById(10L));
        Assertions.assertEquals("Summary does not exist", exception.getMessage());

    }
}
