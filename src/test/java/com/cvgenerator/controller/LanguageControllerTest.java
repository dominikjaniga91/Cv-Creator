package com.cvgenerator.controller;

import com.cvgenerator.domain.entity.Language;
import com.cvgenerator.domain.entity.Project;
import com.cvgenerator.domain.enums.LanguageLevel;
import com.cvgenerator.service.implementation.LanguageServiceImpl;
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
@DisplayName("Request to language controller using http method")
public class LanguageControllerTest {

    @Autowired private MockMvc mockMvc;
    @MockBean private LanguageServiceImpl languageService;
    private String token;
    private Language language;

    @BeforeEach
    void setUp() {

        token = Jwts
                .builder()
                .claim("role", "USER")
                .setSubject("admin")
                .setExpiration(new Date(System.currentTimeMillis() + 86_400_000))
                .signWith(SignatureAlgorithm.HS256, "6fg28#h$h*uiq2con!%^&k5k()_mrj8")
                .compact();

        language = Language.builder()
                .name("English")
                .description(null)
                .level(LanguageLevel.B2)
                .stars(3)
                .build();
    }

    @Test
    @DisplayName("POST should return status 'created' after post Language")
    void shouldReturnStatusCreated_afterPostProject() throws Exception {

        BDDMockito.doNothing().when(languageService).createLanguage(anyLong(), any(Language.class));

        mockMvc.perform(post("/api/cv/language/{cvId}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(language))
                .header("Authorization", "Bearer " + token)
                .header("Access-Control-Expose-Headers", "Authorization"))
                .andExpect(status().isCreated())
                .andDo(print());

        BDDMockito.verify(languageService, Mockito.times(1)).createLanguage(anyLong(), any(Language.class));
        BDDMockito.verifyNoMoreInteractions(languageService);
    }

    @Test
    @DisplayName("PUT should return status 'ok' after update Language")
    void shouldReturnStatusOK_afterUpdateProject() throws Exception {

        BDDMockito.doNothing().when(languageService).updateLanguage(ArgumentMatchers.any(Language.class));

        mockMvc.perform(put("/api/cv/language")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(language))
                .header("Authorization", "Bearer " + token)
                .header("Access-Control-Expose-Headers", "Authorization"))
                .andExpect(status().isOk())
                .andDo(print());

        BDDMockito.verify(languageService, Mockito.times(1)).updateLanguage(ArgumentMatchers.any(Language.class));
        BDDMockito.verifyNoMoreInteractions(languageService);
    }

    @Test
    @DisplayName("DELETE should return status 'ok' after delete Language")
    void shouldReturnStatusOK_afterDeleteProject() throws Exception {

        BDDMockito.doNothing().when(languageService).deleteLanguageById(1L);

        mockMvc.perform(delete("/api/cv/language/{id}", 1L)
                .header("Authorization", "Bearer " + token)
                .header("Access-Control-Expose-Headers", "Authorization"))
                .andExpect(status().isOk())
                .andDo(print());

        BDDMockito.verify(languageService, Mockito.times(1)).deleteLanguageById(1L);
        BDDMockito.verifyNoMoreInteractions(languageService);
    }

}
