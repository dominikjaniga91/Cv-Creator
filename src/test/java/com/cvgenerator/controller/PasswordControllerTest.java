package com.cvgenerator.controller;

import com.cvgenerator.domain.entity.Token;
import com.cvgenerator.domain.entity.User;
import com.cvgenerator.repository.UserRepository;
import com.cvgenerator.service.implementation.TokenServiceImpl;
import com.cvgenerator.utils.service.implementation.MailServiceImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDateTime;
import java.util.Date;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
@DisplayName("Request to password controller using http method")
@WithMockUser(username = "admin", password = "admin")
public class PasswordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenServiceImpl tokenService;

    @MockBean
    private MailServiceImpl mailService;
    private String apiToken;

    @BeforeEach
    void setUp() {
        apiToken = Jwts
                .builder()
                .claim("role", "USER")
                .setSubject("admin")
                .setExpiration(new Date(System.currentTimeMillis() + 86_400_000))
                .signWith(SignatureAlgorithm.HS256, "6fg28#h$h*uiq2con!%^&k5k()_mrj8")
                .compact();
    }

    @Test
    @DisplayName("GET should status Ok after request to password reset endpoint")
    void shouldReturnStatusOk_afterRequestToPasswordResetEndpoint() throws Exception {

        BDDMockito.doNothing().when(mailService).sendPasswordResetEmail(ArgumentMatchers.any(User.class));
        mockMvc.perform(get("/api/password-reset/{userId}", 1L)
                .header("Authorization", "Bearer " + apiToken)
                .header("Access-Control-Expose-Headers", "Authorization"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("POST should return status Ok and change password after request")
    void shouldReturnStatusOk_afterRequestForPasswordChange() throws Exception {

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
        userRepository.save(user);
        Token token = tokenService.createPasswordResetToken(user);
        String tokenValue = token.getValue();

        String newPassword = "{\n" +
                "\t\"password\": \"newpassword\"\n" +
                "}";

        mockMvc.perform(post("/api/new-password")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newPassword)
                .param("value", tokenValue)
                .header("Authorization", "Bearer " + apiToken)
                .header("Access-Control-Expose-Headers", "Authorization"))
                .andExpect(status().isOk())
                .andExpect(mvcResult -> Assertions.assertEquals(mvcResult.getResponse().getContentAsString(), "Your password has been changed"));

    }

    @Test
    @DisplayName("POST should return 'Not-found' after request for password change with invalid token")
    void shouldReturnStatusBadRequest_afterRequestForPasswordChange() throws Exception {


        String newPassword = "{\n" +
                "\t\"password\": \"newpassword\"\n" +
                "}";

        mockMvc.perform(post("/api/new-password")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newPassword)
                .param("value", "validToken")
                .header("Authorization", "Bearer " + apiToken)
                .header("Access-Control-Expose-Headers", "Authorization"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorMessage", Matchers.is("Token does not exist")));

    }

}
