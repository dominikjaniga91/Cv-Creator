package com.cvgenerator.controller;

import com.cvgenerator.domain.entity.Clause;
import com.cvgenerator.service.implementation.ClauseServiceImpl;
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
@DisplayName("Request to clause controller using http method")
public class ClauseControllerTest {

    @Autowired private MockMvc mockMvc;
    @MockBean private ClauseServiceImpl clauseService;
    private String token;
    private Clause clause;

    @BeforeEach
    void setUp() {

        token = Jwts
                .builder()
                .claim("role", "USER")
                .setSubject("admin")
                .setExpiration(new Date(System.currentTimeMillis() + 86_400_000))
                .signWith(SignatureAlgorithm.HS256, "6fg28#h$h*uiq2con!%^&k5k()_mrj8")
                .compact();

        clause = Clause.builder()
        .value("Test clause")
        .build();
    }

    @Test
    @DisplayName("POST should return status 'created' after post Clause")
    void shouldReturnStatusCreated_afterPostClause() throws Exception {

        BDDMockito.doNothing().when(clauseService).createClause(anyLong(), any(Clause.class));

        mockMvc.perform(post("/api/cv/clause/{cvId}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(clause))
                .header("Authorization", "Bearer " + token)
                .header("Access-Control-Expose-Headers", "Authorization"))
                .andExpect(status().isCreated())
                .andDo(print());

        BDDMockito.verify(clauseService, Mockito.times(1)).createClause(anyLong(), any(Clause.class));
        BDDMockito.verifyNoMoreInteractions(clauseService);
    }

    @Test
    @DisplayName("PUT should return status 'ok' after update Clause")
    void shouldReturnStatusOK_afterUpdateClause() throws Exception {

        BDDMockito.doNothing().when(clauseService).updateClause(ArgumentMatchers.any(Clause.class));

        mockMvc.perform(put("/api/cv/clause")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(clause))
                .header("Authorization", "Bearer " + token)
                .header("Access-Control-Expose-Headers", "Authorization"))
                .andExpect(status().isOk())
                .andDo(print());

        BDDMockito.verify(clauseService, Mockito.times(1)).updateClause(ArgumentMatchers.any(Clause.class));
        BDDMockito.verifyNoMoreInteractions(clauseService);
    }

    @Test
    @DisplayName("DELETE should return status 'ok' after delete Clause")
    void shouldReturnStatusOK_afterDeleteClause() throws Exception {

        BDDMockito.doNothing().when(clauseService).deleteClauseById(1L);

        mockMvc.perform(delete("/api/cv/clause/{id}", 1L)
                .header("Authorization", "Bearer " + token)
                .header("Access-Control-Expose-Headers", "Authorization"))
                .andExpect(status().isOk())
                .andDo(print());

        BDDMockito.verify(clauseService, Mockito.times(1)).deleteClauseById(1L);
        BDDMockito.verifyNoMoreInteractions(clauseService);
    }

}
