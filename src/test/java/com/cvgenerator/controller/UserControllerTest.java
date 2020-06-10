package com.cvgenerator.controller;

import com.cvgenerator.domain.dto.UserDto;
import com.cvgenerator.service.implementation.UserServiceImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Date;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Request to user controller using http method")
public class UserControllerTest {



    @Autowired private MockMvc mockMvc;
    @MockBean private UserServiceImpl userService;
    private String token;
    private UserDto userDto;

    @BeforeEach
    void setUp() {

        userDto = new UserDto.UserDtoBuilder()
                                        .setId(1L)
                                        .setFirstName("Dominik")
                                        .setLastName("Janiga")
                                        .setEmail("dominikjaniga91@gmail.com")
                                        .setRole("USER")
                                        .setActive(true)
                                        .buildUserDto();

        token = Jwts
                .builder()
                .claim("role", "USER")
                .setSubject("admin")
                .setExpiration(new Date(System.currentTimeMillis() + 86_400_000))
                .signWith(SignatureAlgorithm.HS256, "6fg28#h$h*uiq2con!%^&k5k()_mrj8")
                .compact();
    }


    @Test
    @WithMockUser(username = "admin", password = "admin")
    @DisplayName("GET should return user account details")
    void shouldReturnUserDetails_afterRequestToEndpoint() throws Exception {

        BDDMockito.given(userService.findUserById(1L)).willReturn(userDto);

        mockMvc.perform(get("/api/user/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token)
                .header("Access-Control-Expose-Headers", "Authorization"))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(System.out::println)
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstName", is("Dominik")))
                .andExpect(jsonPath("$.lastName", is("Janiga")))
                .andExpect(jsonPath("$.email", is("dominikjaniga91@gmail.com")))
                .andExpect(jsonPath("$.role", is("USER")))
                .andExpect(jsonPath("$.active", is(true)));

    }

}
