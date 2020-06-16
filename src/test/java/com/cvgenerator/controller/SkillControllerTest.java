package com.cvgenerator.controller;

import com.cvgenerator.domain.entity.Skill;
import com.cvgenerator.service.implementation.SkillServiceImpl;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@WithMockUser(username = "admin", password = "admin")
@DisplayName("Request to skill controller using http method")
public class SkillControllerTest {

    @Autowired private MockMvc mockMvc;
    @MockBean private SkillServiceImpl skillService;
    private String token;
    private Skill skill;

    @BeforeEach
    void setUp() {

        token = Jwts
                .builder()
                .claim("role", "USER")
                .setSubject("admin")
                .setExpiration(new Date(System.currentTimeMillis() + 86_400_000))
                .signWith(SignatureAlgorithm.HS256, "6fg28#h$h*uiq2con!%^&k5k()_mrj8")
                .compact();

        skill = Skill.builder()
                .name("Samoorganizacja")
                .description(null)
                .level("Zaawansowany")
                .stars(4)
                .build();
    }

    @Test
    @DisplayName("POST should return status 'created' after post Skill")
    void shouldReturnStatusCreated_afterPostSkill() throws Exception {

        BDDMockito.doNothing().when(skillService).createSkill(anyLong(), any(Skill.class));

        mockMvc.perform(post("/api/cv/skill/{cvId}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(skill))
                .header("Authorization", "Bearer " + token)
                .header("Access-Control-Expose-Headers", "Authorization"))
                .andExpect(status().isCreated())
                .andDo(print());

        BDDMockito.verify(skillService, Mockito.times(1)).createSkill(anyLong(), any(Skill.class));
        BDDMockito.verifyNoMoreInteractions(skillService);
    }

    @Test
    @DisplayName("PUT should return status 'ok' after update Skill")
    void shouldReturnStatusOK_afterUpdateUser() throws Exception {

        BDDMockito.doNothing().when(skillService).updateSkill(ArgumentMatchers.any(Skill.class));

        mockMvc.perform(put("/api/cv/skill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(skill))
                .header("Authorization", "Bearer " + token)
                .header("Access-Control-Expose-Headers", "Authorization"))
                .andExpect(status().isOk())
                .andDo(print());

        BDDMockito.verify(skillService, Mockito.times(1)).updateSkill(ArgumentMatchers.any(Skill.class));
        BDDMockito.verifyNoMoreInteractions(skillService);
    }

    @Test
    @DisplayName("DELETE should return status 'ok' after delete Skill")
    void shouldReturnStatusOK_afterDeleteUser() throws Exception {

        BDDMockito.doNothing().when(skillService).deleteSkillById(1L);

        mockMvc.perform(delete("/api/cv/skill/{id}", 1L)
                .header("Authorization", "Bearer " + token)
                .header("Access-Control-Expose-Headers", "Authorization"))
                .andExpect(status().isOk())
                .andDo(print());

        BDDMockito.verify(skillService, Mockito.times(1)).deleteSkillById(1L);
        BDDMockito.verifyNoMoreInteractions(skillService);
    }

}
