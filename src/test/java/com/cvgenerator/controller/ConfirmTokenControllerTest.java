package com.cvgenerator.controller;

import com.cvgenerator.domain.entity.User;
import com.cvgenerator.service.implementation.TokenServiceImpl;
import com.cvgenerator.utils.service.implementation.MailServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDateTime;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@DisplayName("Request to confirm controller using http method")
public class ConfirmTokenControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TokenServiceImpl tokenService;

    @MockBean
    private MailServiceImpl mailService;

    @Test
    @DisplayName("should return status OK and activate account after request")
    void shouldReturnStatusOkAndActivateUser_afterRequest() throws Exception {

        User user = new User.UserBuilder()
                .setId(1L)
                .setFirstName("Dominik")
                .setLastName("Janiga")
                .setEmail("dominikjaniga91@gmail.com")
                .setRole("ROLE_USER")
                .setPassword("dominik123")
                .setActive(true)
                .setRegistration(LocalDateTime.now())
                .buildUserDto();

        String token = tokenService.createConfirmationToken(user);
        BDDMockito.doNothing().when(mailService).sendConfirmationEmail(ArgumentMatchers.any(User.class));

        mockMvc.perform(get("/api/token")
                .param("value", token))
                .andExpect(status().isOk())
                .andExpect(mvcResult -> Assertions.assertEquals(mvcResult.getResponse().getContentAsString(), "Your account is active"));

    }

    @Test
    @DisplayName("should return status 'Bad request' after request")
    void shouldReturnStatusBadRequest_afterRequest() throws Exception {

        mockMvc.perform(get("/api/token")
                .param("value", "validToken"))
                .andExpect(status().isBadRequest())
                .andExpect(mvcResult -> Assertions.assertEquals(mvcResult.getResponse().getContentAsString(), "Confirmation link doesn't exist"));

    }
}
