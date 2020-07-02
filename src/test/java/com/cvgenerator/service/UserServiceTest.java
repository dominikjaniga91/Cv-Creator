package com.cvgenerator.service;

import com.cvgenerator.domain.entity.User;
import com.cvgenerator.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import javax.transaction.Transactional;
import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
@DisplayName("User service test should return")
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;
    private User user;

    @BeforeEach
    void setUp() {

        user = new User.UserBuilder()
                .setId(1L)
                .setFirstName("Dominik")
                .setLastName("Janiga")
                .setEmail("dominikjaniga91@gmail.com")
                .setRole("USER")
                .setActive(true)
                .buildUserDto();

        userRepository.save(user);
    }

    @Test
    @Transactional
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @DisplayName("one user with name 'Dominik' ")
    void shouldReturnOneUser_afterGetUsersFromDatabase(){
        String expected = "Dominik";
        List<User> users = userRepository.findAll();
        User foundedUser = users.get(0);

        Assertions.assertEquals(1, users.size());
        Assertions.assertEquals(expected, foundedUser.getFirstName());
    }

    @Test
    @Transactional
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @DisplayName("zero users after delete user from database ")
    void shouldReturnZero_afterDeleteUserFromDatabase(){
        int expected = 0;
        userRepository.deleteById(1L);
        List<User> users = userRepository.findAll();

        Assertions.assertEquals(expected, users.size());
    }
}
