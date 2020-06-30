package com.cvgenerator.service.implementation;

import com.cvgenerator.config.Messages;
import com.cvgenerator.domain.entity.Project;
import com.cvgenerator.domain.entity.UserCv;
import com.cvgenerator.exceptions.notfound.ProjectNotFoundException;
import com.cvgenerator.exceptions.notfound.UserCvNotFoundException;
import com.cvgenerator.repository.ProjectRepository;
import com.cvgenerator.repository.UserCvRepository;
import com.cvgenerator.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final UserCvRepository userCvRepository;
    private final Messages messages;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository,
                              UserCvRepository userCvRepository,
                              Messages messages) {
        this.projectRepository = projectRepository;
        this.userCvRepository = userCvRepository;
        this.messages = messages;
    }

    @Override
    public void createProject(Long userCvId, Project project) {

        UserCv userCv = userCvRepository.findById(userCvId).orElseThrow(() -> new UserCvNotFoundException(messages.get("userCv.notfound")));
        project.setUserCv(userCv);
        projectRepository.save(project);
    }

    @Override
    public void updateProject(Project project) {
        Long id = project.getId();
        Project foundedProject = projectRepository.findById(id).orElseThrow(() -> new ProjectNotFoundException(messages.get("project.notfound")));

        foundedProject.setName(project.getName());
        foundedProject.setStartDate(project.getStartDate());
        foundedProject.setFinishDate(project.getFinishDate());
        foundedProject.setDescription(project.getDescription());

        projectRepository.save(foundedProject);
    }

    @Override
    public void deleteProjectById(Long id) {
        try{
            projectRepository.deleteById(id);
        }catch (Exception ex){
            throw new ProjectNotFoundException(messages.get("project.notfound"));
        }

    }
}
