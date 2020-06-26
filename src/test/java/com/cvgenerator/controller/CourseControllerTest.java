package com.cvgenerator.controller;

import com.cvgenerator.domain.entity.Course;
import com.cvgenerator.domain.entity.Experience;
import com.cvgenerator.service.implementation.CourseServiceImpl;
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
@DisplayName("Request to course controller using http method")
public class CourseControllerTest {

    @Autowired private MockMvc mockMvc;
    @MockBean private CourseServiceImpl courseService;
    private String token;
    private Course course;

    @BeforeEach
    void setUp() {

        token = Jwts
                .builder()
                .claim("role", "USER")
                .setSubject("admin")
                .setExpiration(new Date(System.currentTimeMillis() + 86_400_000))
                .signWith(SignatureAlgorithm.HS256, "6fg28#h$h*uiq2con!%^&k5k()_mrj8")
                .compact();

        course = Course.builder()
        .school("AGH")
        .city("Kraków")
        .startDate(LocalDate.now())
        .finishDate(LocalDate.now())
        .description("Elektrotechnika")
        .build();
    }

    @Test
    @DisplayName("POST should return status 'created' after post Course")
    void shouldReturnStatusCreated_afterPostCourse() throws Exception {

        BDDMockito.doNothing().when(courseService).createCourse(anyLong(), any(Course.class));

        mockMvc.perform(post("/api/cv/course/{cvId}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(course))
                .header("Authorization", "Bearer " + token)
                .header("Access-Control-Expose-Headers", "Authorization"))
                .andExpect(status().isCreated())
                .andDo(print());

        BDDMockito.verify(courseService, Mockito.times(1)).createCourse(anyLong(), any(Course.class));
        BDDMockito.verifyNoMoreInteractions(courseService);
    }

    @Test
    @DisplayName("PUT should return status 'ok' after update Course")
    void shouldReturnStatusOK_afterUpdateCourse() throws Exception {

        BDDMockito.doNothing().when(courseService).updateCourse(ArgumentMatchers.any(Course.class));

        mockMvc.perform(put("/api/cv/course")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(course))
                .header("Authorization", "Bearer " + token)
                .header("Access-Control-Expose-Headers", "Authorization"))
                .andExpect(status().isOk())
                .andDo(print());

        BDDMockito.verify(courseService, Mockito.times(1)).updateCourse(ArgumentMatchers.any(Course.class));
        BDDMockito.verifyNoMoreInteractions(courseService);
    }

    @Test
    @DisplayName("DELETE should return status 'ok' after delete Course")
    void shouldReturnStatusOK_afterDeleteCourse() throws Exception {

        BDDMockito.doNothing().when(courseService).deleteCourseById(1L);

        mockMvc.perform(delete("/api/cv/course/{id}", 1L)
                .header("Authorization", "Bearer " + token)
                .header("Access-Control-Expose-Headers", "Authorization"))
                .andExpect(status().isOk())
                .andDo(print());

        BDDMockito.verify(courseService, Mockito.times(1)).deleteCourseById(1L);
        BDDMockito.verifyNoMoreInteractions(courseService);
    }

}
