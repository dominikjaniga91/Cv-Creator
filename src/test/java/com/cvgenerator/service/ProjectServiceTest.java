package com.cvgenerator.service;

import com.cvgenerator.domain.dto.UserCvDto;
import com.cvgenerator.domain.dto.UserDto;
import com.cvgenerator.domain.entity.Project;
import com.cvgenerator.domain.entity.User;
import com.cvgenerator.exceptions.notfound.ProjectNotFoundException;
import com.cvgenerator.repository.ProjectRepository;
import com.cvgenerator.service.implementation.ProjectServiceImpl;
import com.cvgenerator.service.implementation.UserCvServiceImpl;
import com.cvgenerator.service.implementation.UserServiceImpl;
import com.cvgenerator.utils.service.implementation.MailServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class ProjectServiceTest {

    @Autowired private ProjectServiceImpl projectService;
    @Autowired private ProjectRepository repository;
    @Autowired private UserServiceImpl userService;
    @Autowired private UserCvServiceImpl userCvService;
    @MockBean private MailServiceImpl mailService;
    private Project project;

    @BeforeEach
    void setUp() {

        BDDMockito.doNothing().when(mailService).sendConfirmationEmail(ArgumentMatchers.any(User.class));

        UserCvDto userCvDto = new UserCvDto.UserCvDtoBuilder()
                .setId(1L)
                .setName("Dominik cv")
                .setTemplateName("Aquarius")
                .buildUserCvDto();

        UserDto userDto = new UserDto.UserDtoBuilder()
                .setId(1L)
                .setFirstName("Dominik")
                .setLastName("Janiga")
                .setEmail("dominikjaniga91@gmail.com")
                .setPassword("dominik123")
                .setRole("USER")
                .setActive(true)
                .buildUserDto();

        project = Project.builder()
                .id(1L)
                .name("mydatabase.com")
                .description("Spring database app")
                .build();

        userService.saveUser(userDto);
        userCvService.saveUserCv(1L, userCvDto);
        projectService.createProject(1L, project);
    }


    @Test
    @DisplayName("Should return appropriate project name from database")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void shouldReturnAppropriateProjectNameFromDatabase(){
        Project foundedProject = repository.findById(1L).orElseThrow();
        assertEquals("mydatabase.com", foundedProject.getName());
    }

    @Test
    @DisplayName("Should return new project name after update")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void shouldReturnNewProjectNameAfterUpdate(){

        String expected = "bestapp.com";
        project.setName(expected);
        projectService.updateProject(project);
        Project foundedProject= repository.findById(1L).orElseThrow();
        assertEquals(expected, foundedProject.getName());
    }

    @Test
    @DisplayName("Should thrown an exception after try to delete non existing project ")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void shouldThrownAnException_afterTryToDeleteNonExistingProject(){

        ProjectNotFoundException exception = assertThrows(ProjectNotFoundException.class, () -> projectService.deleteProjectById(10L));
        assertEquals("Project does not exist", exception.getMessage());
    }
}
