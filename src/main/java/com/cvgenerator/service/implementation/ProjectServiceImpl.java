package com.cvgenerator.service.implementation;

import com.cvgenerator.domain.entity.Project;
import com.cvgenerator.domain.entity.UserCv;
import com.cvgenerator.repository.ProjectRepository;
import com.cvgenerator.repository.UserCvRepository;
import com.cvgenerator.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final UserCvRepository userCvRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository, UserCvRepository userCvRepository) {
        this.projectRepository = projectRepository;
        this.userCvRepository = userCvRepository;
    }

    @Override
    public void createProject(Long userCvId, Project project) {

        UserCv userCv = userCvRepository.findById(userCvId).orElseThrow();
        project.setUserCv(userCv);
        projectRepository.save(project);
    }
}
