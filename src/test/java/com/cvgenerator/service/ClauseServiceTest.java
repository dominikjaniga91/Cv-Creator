package com.cvgenerator.service;

import com.cvgenerator.domain.dto.UserCvDto;
import com.cvgenerator.domain.dto.UserDto;
import com.cvgenerator.domain.entity.Clause;
import com.cvgenerator.domain.entity.User;
import com.cvgenerator.repository.ClauseRepository;
import com.cvgenerator.service.implementation.ClauseServiceImpl;
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
public class ClauseServiceTest {

    @Autowired private ClauseServiceImpl clauseService;
    @Autowired private ClauseRepository repository;
    @Autowired private UserServiceImpl userService;
    @Autowired private UserCvServiceImpl userCvService;
    @MockBean private MailServiceImpl mailService;
    private Clause clause;

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

        clause = Clause.builder()
                        .id(1L)
                        .value("Lorem ipsum dolor sit amet.")
                        .build();

        userService.saveUser(userDto);
        userCvService.saveUserCv(1L, userCvDto);
        clauseService.createClause(1L, clause);

    }

    @Test
    @DisplayName("Should return clause value from database")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void shouldReturnClauseValueFromDatabase(){

        Clause foundedClause = repository.findById(1L).orElseThrow();
        Assertions.assertEquals("Lorem ipsum dolor sit amet.", foundedClause.getValue());
    }

    @Test
    @DisplayName("Should return new clause value after update")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void shouldReturnNewClauseValueAfterUpdate(){

        String expected = "There are many ways you can map a one-to-one relationship with Hibernate";
        clause.setValue(expected);
        clauseService.updateClause(clause);
        Clause foundedClause = repository.findById(1L).orElseThrow();
        Assertions.assertEquals(expected, foundedClause.getValue());
    }
}
