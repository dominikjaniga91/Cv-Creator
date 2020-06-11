package com.cvgenerator.controller;

import com.cvgenerator.domain.dto.*;
import com.cvgenerator.domain.entity.Address;
import com.cvgenerator.domain.enums.LanguageLevel;
import com.cvgenerator.service.implementation.UserCvServiceImpl;
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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "admin", password = "admin", roles={"ADMIN"})
public class UserCvControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserCvServiceImpl userCvService;
    private UserCvDto userCvDto;
    private String token;

    @BeforeEach
    void setUp() {

        List<CourseDto> courseDtos = List.of(new CourseDto(1L, "Altkom Akademia", "Kraków", LocalDate.of(2020,1,1), LocalDate.of(2020,6,30), "Java programming"));
        List<EducationDto> educationDtos = List.of(new EducationDto(1L, "AGH", "Kraków", LocalDate.of(2015,1,1), LocalDate.of(2020,6,30), "Master Degree", "Elektrotechnika", null));
        List<ExperienceDto> experienceDtos = List.of(new ExperienceDto(1L, "Jeronimo Martins", "Kraków", "Magazynier", LocalDate.of(2015,1,1), LocalDate.of(2020,6,30), null));
        List<InterestDto> interestDtos = List.of(new InterestDto(1L, "Wspinaczka, Piwo, Koszykówka "));
        List<LanguageDto> languageDtos = List.of(new LanguageDto(1L, "English", null, LanguageLevel.B2, 3));
        List<ProjectDto> projectDtos = List.of(new ProjectDto(1L, "Car Database", LocalDate.of(2015,1,1), LocalDate.of(2020,6,30), "Simple CRUD app"));
        List<SkillDto> skillDtos = List.of(new SkillDto(1L, "Samoorganizacja", null, "Zaawansowany", 4));
        Address address = new Address(1L, "Źródlana", 56, "33-111", "Koszyce Male");
        PersonalDataDto personalDataDto = new PersonalDataDto(1L,"Dominik", "Rafał", "Janiga", "dominikjaniga@gmail.com", "881463195", "Java Developer", null, "https://github.com/dominikjaniga91", null, null, address);

        userCvDto = new UserCvDto(1L,
                                "cv1",
                                "aquarius",
                                "summary template1",
                                "RODO clause1",
                                personalDataDto,
                                courseDtos,
                                projectDtos,
                                skillDtos,
                                languageDtos,
                                interestDtos,
                                educationDtos,
                                experienceDtos);
        token = Jwts
                .builder()
                .claim("role", "USER")
                .setSubject("admin")
                .setExpiration(new Date(System.currentTimeMillis() + 86_400_000))
                .signWith(SignatureAlgorithm.HS256, "6fg28#h$h*uiq2con!%^&k5k()_mrj8")
                .compact();
    }

    @Test
    @DisplayName("GET should return CV with details and all categories")
    void shouldReturnCvWithAllCategories_afterRequestToEndpoint() throws Exception {

        BDDMockito.given(userCvService.getUserCvById(1L)).willReturn(userCvDto);

        mockMvc.perform(get("/api/cv/{id}", 1L)
                .header("Authorization", "Bearer " + token)
                .header("Access-Control-Expose-Headers", "Authorization"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.templateName", is("aquarius")))
                .andExpect(jsonPath("$.personalData.firstName", is("Dominik")))
                .andExpect(jsonPath("$.personalData.address.houseNumber", is(56)))
                .andExpect(jsonPath("$.courses[0].school", is("Altkom Akademia")));

        BDDMockito.verify(userCvService).getUserCvById(1L);
        BDDMockito.verifyNoMoreInteractions(userCvService);
    }
}
