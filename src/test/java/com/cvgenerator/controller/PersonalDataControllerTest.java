package com.cvgenerator.controller;

import com.cvgenerator.domain.entity.PersonalData;
import com.cvgenerator.domain.entity.Project;
import com.cvgenerator.service.implementation.PersonalDataServiceImpl;
import com.cvgenerator.service.implementation.ProjectServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@WithMockUser(username = "admin", password = "admin")
@DisplayName("Request to personal data controller using http method")
public class PersonalDataControllerTest {

    @Autowired private MockMvc mockMvc;
    @MockBean private PersonalDataServiceImpl personalDataService;
    private String token;
    private PersonalData personalData;

    @BeforeEach
    void setUp() {

        token = Jwts
                .builder()
                .claim("role", "USER")
                .setSubject("admin")
                .setExpiration(new Date(System.currentTimeMillis() + 86_400_000))
                .signWith(SignatureAlgorithm.HS256, "6fg28#h$h*uiq2con!%^&k5k()_mrj8")
                .compact();

        personalData = PersonalData.builder()
        .firstName("Dominik")
        .secondName("Rafał")
        .lastName("Janiga")
        .email("dominikjaniga91@gmail.com")
        .phoneNumber("881463106")
        .profession("Java Developer")
        .build();
    }

    @Test
    @DisplayName("POST should return status 'created' after post Personal data")
    void shouldReturnStatusCreated_afterPostPersonalData() throws Exception {

        BDDMockito.doNothing().when(personalDataService).createPersonalData(anyLong(), any(PersonalData.class));

        mockMvc.perform(post("/api/cv/personal-data/{cvId}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(personalData))
                .header("Authorization", "Bearer " + token)
                .header("Access-Control-Expose-Headers", "Authorization"))
                .andExpect(status().isCreated())
                .andDo(print());

        BDDMockito.verify(personalDataService, Mockito.times(1)).createPersonalData(anyLong(), any(PersonalData.class));
        BDDMockito.verifyNoMoreInteractions(personalDataService);
    }

    @Test
    @DisplayName("PUT should return status 'ok' after update Personal data")
    void shouldReturnStatusOK_afterUpdatePersonalData() throws Exception {

        BDDMockito.doNothing().when(personalDataService).updatePersonalData(ArgumentMatchers.any(PersonalData.class));

        mockMvc.perform(put("/api/cv/personal-data")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(personalData))
                .header("Authorization", "Bearer " + token)
                .header("Access-Control-Expose-Headers", "Authorization"))
                .andExpect(status().isOk())
                .andDo(print());

        BDDMockito.verify(personalDataService, Mockito.times(1)).updatePersonalData(ArgumentMatchers.any(PersonalData.class));
        BDDMockito.verifyNoMoreInteractions(personalDataService);
    }

    @Test
    @DisplayName("DELETE should return status 'ok' after delete Personal data")
    void shouldReturnStatusOK_afterDeletePersonalData() throws Exception {

        BDDMockito.doNothing().when(personalDataService).deletePersonalDataById(1L);

        mockMvc.perform(delete("/api/cv/personal-data/{id}", 1L)
                .header("Authorization", "Bearer " + token)
                .header("Access-Control-Expose-Headers", "Authorization"))
                .andExpect(status().isOk())
                .andDo(print());

        BDDMockito.verify(personalDataService, Mockito.times(1)).deletePersonalDataById(1L);
        BDDMockito.verifyNoMoreInteractions(personalDataService);
    }

}
