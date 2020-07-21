package com.cvgenerator.service;

import com.cvgenerator.domain.dto.UserCvDto;
import com.cvgenerator.domain.dto.UserDto;
import com.cvgenerator.domain.entity.Interest;
import com.cvgenerator.domain.entity.User;
import com.cvgenerator.exceptions.notfound.InterestNotFoundException;
import com.cvgenerator.repository.InterestRepository;
import com.cvgenerator.service.implementation.InterestServiceImpl;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class InterestServiceTest {

    @Autowired private InterestServiceImpl interestService;
    @Autowired private InterestRepository repository;
    @Autowired private UserServiceImpl userService;
    @Autowired private UserCvServiceImpl userCvService;
    @MockBean private MailServiceImpl mailService;
    private Interest interest;

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

        interest = Interest.builder()
                .id(1L)
                .name("Basketball")
                .build();

        userService.saveUser(userDto);
        userCvService.saveUserCv(1L, userCvDto);
        interestService.createInterest(1L, interest);
    }

    @Test
    @DisplayName("Should return appropriate interest from database")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void shouldReturnAppropriateInterestFromDatabase(){
        Interest foundedInterest = repository.findById(1L).orElseThrow();
        assertEquals("Basketball", foundedInterest.getName());
    }

    @Test
    @DisplayName("Should return new interest after update")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void shouldReturnNewInterestAfterUpdate(){

        String expected = "Swimming";
        interest.setName(expected);
        interestService.updateInterest(interest);
        Interest foundedInterest = repository.findById(1L).orElseThrow();
        assertEquals(expected, foundedInterest.getName());
    }

    @Test
    @DisplayName("Should thrown an exception after try to delete non existing interest ")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void shouldThrownAnException_afterTryToDeleteNonExistingInterest(){

        InterestNotFoundException exception = assertThrows(InterestNotFoundException.class, () -> interestService.deleteInterestById(10L));
        assertEquals("Interest does not exist", exception.getMessage());
    }
}
