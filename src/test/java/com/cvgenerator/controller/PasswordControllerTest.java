package com.cvgenerator.controller;

import com.cvgenerator.domain.entity.User;
import com.cvgenerator.utils.service.implementation.MailServiceImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
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
import java.util.Date;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@DisplayName("Request to password controller using http method")
public class PasswordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MailServiceImpl mailService;

    @Test
    @DisplayName("GET should status oK after request to password reset endpoint")
    void shouldReturnStatusOk_afterRequestToPasswordResetEndpoint() throws Exception {

        String token = Jwts
                .builder()
                .claim("role", "USER")
                .setSubject("admin")
                .setExpiration(new Date(System.currentTimeMillis() + 86_400_000))
                .signWith(SignatureAlgorithm.HS256, "6fg28#h$h*uiq2con!%^&k5k()_mrj8")
                .compact();

        BDDMockito.doNothing().when(mailService).sendPasswordResetEmail(ArgumentMatchers.any(User.class));


        mockMvc.perform(get("/api/password-reset/{userId}", 1L)
                .header("Authorization", "Bearer " + token)
                .header("Access-Control-Expose-Headers", "Authorization"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
