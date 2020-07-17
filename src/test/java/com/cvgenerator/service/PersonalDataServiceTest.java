package com.cvgenerator.service;

import com.cvgenerator.domain.dto.UserCvDto;
import com.cvgenerator.domain.dto.UserDto;
import com.cvgenerator.domain.entity.Address;
import com.cvgenerator.domain.entity.PersonalData;
import com.cvgenerator.domain.entity.User;
import com.cvgenerator.repository.PersonalDataRepository;
import com.cvgenerator.service.implementation.PersonalDataServiceImpl;
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
public class PersonalDataServiceTest {

    @Autowired private PersonalDataServiceImpl personalDataService;
    @Autowired private PersonalDataRepository personalDataRepository;
    @Autowired private UserCvServiceImpl userCvService;
    @Autowired private UserServiceImpl userService;
    @MockBean private MailServiceImpl mailService;
    private PersonalData personalData;

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

        Address address = new Address(1L, "Źródlana", 56, "Koszyce Małe", "33-111");

        personalData = PersonalData.builder()
                    .id(1L)
                    .email("dominikjaniga91@gmail.com")
                    .address(address)
                    .build();

        userService.saveUser(userDto);
        userCvService.saveUserCv(1L, userCvDto);
        personalDataService.createPersonalData(1L, personalData);
    }

    @Test
    @DisplayName("Should return appropriate personal data email from database")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void shouldReturnAppropriatePersonalDataEmailFromDB(){

        PersonalData foundedPersonalData = personalDataRepository.findById(1L).orElseThrow();
        Assertions.assertEquals("dominikjaniga91@gmail.com", foundedPersonalData.getEmail());
    }
}
