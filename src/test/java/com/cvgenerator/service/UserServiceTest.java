package com.cvgenerator.service;

import com.cvgenerator.domain.dto.UserDto;
import com.cvgenerator.domain.entity.User;
import com.cvgenerator.exceptions.notfound.UserNotFoundException;
import com.cvgenerator.service.dtoConverters.UserDtoConverter;
import com.cvgenerator.service.implementation.UserServiceImpl;
import com.cvgenerator.utils.service.implementation.MailServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import javax.transaction.Transactional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DisplayName("User service test should return")
public class UserServiceTest {

    @Autowired private UserServiceImpl userService;
    @Autowired private UserDtoConverter converter;
    @Autowired private PasswordEncoder passwordEncoder;

    @MockBean
    private MailServiceImpl mailService;

    private UserDto userDto;

    @BeforeEach
    void setUp() {

        BDDMockito.doNothing().when(mailService).sendConfirmationEmail(ArgumentMatchers.any(User.class));

        userDto = new UserDto.UserDtoBuilder()
                .setId(1L)
                .setFirstName("Dominik")
                .setLastName("Janiga")
                .setEmail("dominikjaniga91@gmail.com")
                .setPassword("dominik123")
                .setRole("USER")
                .setActive(true)
                .buildUserDto();

        userService.saveUser(userDto);
    }

    @Test
    @Transactional
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @DisplayName("one user with name 'Dominik' ")
    void shouldReturnOneUser_afterGetUsersFromDatabase(){
        String expected = "Dominik";
        UserDto user = userService.findUserById(1L);

        Assertions.assertEquals(expected, user.getFirstName());
    }

    @Test
    @Transactional
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @DisplayName("an exception after delete user from database ")
    void shouldThrownException_afterDeleteUserFromDatabase(){
        userService.deleteUserAccount(1L, "dominik123");
        Assertions.assertThrows(UserNotFoundException.class, () -> userService.findUserById(1L));
    }

    @Test
    @Transactional
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @DisplayName("new password after change")
    void shouldReturnNewPassword_afterChange(){
        User user = converter.convertToEntity(userDto);
        userService.updateUserPassword(user, "dominik1234");
        UserDto foundedUser = userService.findUserById(1L);

        Assertions.assertTrue(passwordEncoder.matches("dominik1234", foundedUser.getPassword()));
    }

    @Test
    @Transactional
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @DisplayName("different user name after update user")
    void shouldReturnDifferentName_AfterUpdateUser(){

        userDto.setFirstName("Maciek");
        userService.updateUser(userDto);
        UserDto newUserDto = userService.findUserById(1L);

        Assertions.assertEquals("Maciek",newUserDto.getFirstName());

    }
}
