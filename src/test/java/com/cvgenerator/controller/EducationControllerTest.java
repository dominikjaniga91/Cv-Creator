package com.cvgenerator.controller;

import com.cvgenerator.domain.entity.Education;
import com.cvgenerator.domain.entity.Experience;
import com.cvgenerator.service.implementation.EducationServiceImpl;
import com.cvgenerator.service.implementation.ExperienceServiceImpl;
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
@DisplayName("Request to education controller using http method")
public class EducationControllerTest {

    @Autowired private MockMvc mockMvc;
    @MockBean private EducationServiceImpl educationService;
    private String token;
    private Education education;

    @BeforeEach
    void setUp() {

        token = Jwts
                .builder()
                .claim("role", "USER")
                .setSubject("admin")
                .setExpiration(new Date(System.currentTimeMillis() + 86_400_000))
                .signWith(SignatureAlgorithm.HS256, "6fg28#h$h*uiq2con!%^&k5k()_mrj8")
                .compact();

        education = Education.builder()
                            .school("AGH")
                            .city("Kraków")
                            .startDate(LocalDate.now())
                            .finishDate(LocalDate.now())
                            .degree("Master degree")
                            .specialization("Automatics")
                            .description(null)
                            .build();
    }

    @Test
    @DisplayName("POST should return status 'created' after post Education")
    void shouldReturnStatusCreated_afterPostEducation() throws Exception {

        BDDMockito.doNothing().when(educationService).createEducation(anyLong(), any(Education.class));

        mockMvc.perform(post("/api/cv/education/{cvId}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(education))
                .header("Authorization", "Bearer " + token)
                .header("Access-Control-Expose-Headers", "Authorization"))
                .andExpect(status().isCreated())
                .andDo(print());

        BDDMockito.verify(educationService, Mockito.times(1)).createEducation(anyLong(), any(Education.class));
        BDDMockito.verifyNoMoreInteractions(educationService);
    }

    @Test
    @DisplayName("PUT should return status 'ok' after update Education")
    void shouldReturnStatusOK_afterUpdateEducation() throws Exception {

        BDDMockito.doNothing().when(educationService).updateEducation(ArgumentMatchers.any(Education.class));

        mockMvc.perform(put("/api/cv/education")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(education))
                .header("Authorization", "Bearer " + token)
                .header("Access-Control-Expose-Headers", "Authorization"))
                .andExpect(status().isOk())
                .andDo(print());

        BDDMockito.verify(educationService, Mockito.times(1)).updateEducation(ArgumentMatchers.any(Education.class));
        BDDMockito.verifyNoMoreInteractions(educationService);
    }

    @Test
    @DisplayName("DELETE should return status 'ok' after delete Education")
    void shouldReturnStatusOK_afterDeleteEducation() throws Exception {

        BDDMockito.doNothing().when(educationService).deleteEducationById(1L);

        mockMvc.perform(delete("/api/cv/education/{id}", 1L)
                .header("Authorization", "Bearer " + token)
                .header("Access-Control-Expose-Headers", "Authorization"))
                .andExpect(status().isOk())
                .andDo(print());

        BDDMockito.verify(educationService, Mockito.times(1)).deleteEducationById(1L);
        BDDMockito.verifyNoMoreInteractions(educationService);
    }

}
