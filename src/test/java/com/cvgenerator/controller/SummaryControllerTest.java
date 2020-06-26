package com.cvgenerator.controller;

import com.cvgenerator.domain.entity.Clause;
import com.cvgenerator.domain.entity.Summary;
import com.cvgenerator.service.implementation.ClauseServiceImpl;
import com.cvgenerator.service.implementation.SummaryServiceImpl;
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
@DisplayName("Request to summary controller using http method")
public class SummaryControllerTest {

    @Autowired private MockMvc mockMvc;
    @MockBean private SummaryServiceImpl summaryService;
    private String token;
    private Summary summary;

    @BeforeEach
    void setUp() {

        token = Jwts
                .builder()
                .claim("role", "USER")
                .setSubject("admin")
                .setExpiration(new Date(System.currentTimeMillis() + 86_400_000))
                .signWith(SignatureAlgorithm.HS256, "6fg28#h$h*uiq2con!%^&k5k()_mrj8")
                .compact();

        summary = Summary.builder()
                        .value("Test summary")
                        .build();
    }

    @Test
    @DisplayName("POST should return status 'created' after post new Summary")
    void shouldReturnStatusCreated_afterPostSummary() throws Exception {

        BDDMockito.doNothing().when(summaryService).createSummary(anyLong(), any(Summary.class));

        mockMvc.perform(post("/api/cv/summary/{cvId}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(summary))
                .header("Authorization", "Bearer " + token)
                .header("Access-Control-Expose-Headers", "Authorization"))
                .andExpect(status().isCreated())
                .andDo(print());

        BDDMockito.verify(summaryService, Mockito.times(1)).createSummary(anyLong(), any(Summary.class));
        BDDMockito.verifyNoMoreInteractions(summaryService);
    }

    @Test
    @DisplayName("PUT should return status 'ok' after update Summary")
    void shouldReturnStatusOK_afterUpdateSummary() throws Exception {

        BDDMockito.doNothing().when(summaryService).updateSummary(ArgumentMatchers.any(Summary.class));

        mockMvc.perform(put("/api/cv/summary")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(summary))
                .header("Authorization", "Bearer " + token)
                .header("Access-Control-Expose-Headers", "Authorization"))
                .andExpect(status().isOk())
                .andDo(print());

        BDDMockito.verify(summaryService, Mockito.times(1)).updateSummary(ArgumentMatchers.any(Summary.class));
        BDDMockito.verifyNoMoreInteractions(summaryService);
    }

    @Test
    @DisplayName("DELETE should return status 'ok' after delete Summary")
    void shouldReturnStatusOk_afterDeleteSummary() throws Exception {

        BDDMockito.doNothing().when(summaryService).deleteSummaryById(1L);

        mockMvc.perform(delete("/api/cv/summary/{id}", 1L)
                .header("Authorization", "Bearer " + token)
                .header("Access-Control-Expose-Headers", "Authorization"))
                .andExpect(status().isOk())
                .andDo(print());

        BDDMockito.verify(summaryService, Mockito.times(1)).deleteSummaryById(1L);
        BDDMockito.verifyNoMoreInteractions(summaryService);
    }

}
