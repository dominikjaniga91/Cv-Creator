package com.cvgenerator.cvgenerator.service.implementation;

import com.cvgenerator.cvgenerator.domain.entity.Project;
import com.cvgenerator.cvgenerator.domain.entity.UserCv;
import com.cvgenerator.cvgenerator.repository.ProjectRepository;
import com.cvgenerator.cvgenerator.repository.SkillRepository;
import com.cvgenerator.cvgenerator.repository.UserCvRepository;
import com.cvgenerator.cvgenerator.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {

    private ProjectRepository projectRepository;
    private UserCvRepository userCvRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository, UserCvRepository userCvRepository) {
        this.projectRepository = projectRepository;
        this.userCvRepository = userCvRepository;
    }

    @Override
    public void saveProject(Long userCvId, Project project) {

        UserCv userCv = userCvRepository.findById(userCvId).orElseThrow();
        project.setUserCv(userCv);
        projectRepository.save(project);
    }
}
