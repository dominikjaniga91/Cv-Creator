package com.cvgenerator.service;

import com.cvgenerator.domain.entity.Token;
import com.cvgenerator.domain.entity.User;
import com.cvgenerator.domain.enums.TokenType;
import com.cvgenerator.repository.TokenRepository;
import com.cvgenerator.service.dtoConverters.UserDtoConverter;
import com.cvgenerator.service.implementation.TokenServiceImpl;
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
public class TokenServiceTest {

    @Autowired private TokenServiceImpl tokenService;
    @Autowired private UserDtoConverter converter;
    @Autowired private TokenRepository repository;
    @Autowired private UserServiceImpl userService;
    @MockBean private MailServiceImpl mailService;
    private User user;


    @BeforeEach
    void setUp(){

        BDDMockito.doNothing().when(mailService).sendConfirmationEmail(ArgumentMatchers.any(User.class));
        user = new User.UserBuilder()
                .setId(1L)
                .setFirstName("Dominik")
                .setPassword("dominik123")
                .setLastName("Janiga")
                .buildUserDto();

        userService.saveUser(converter.convertToDto(user));
    }

    @Test
    @DisplayName("Should return token type after save confirm token to database")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void shouldReturnTokenType_afterSaveTokenToDatabase(){

        tokenService.createConfirmationToken(user);
        Token token = repository.findById(1L).orElseThrow();
        Assertions.assertEquals(TokenType.VERIFICATION, token.getTokenType());
    }
}
