package com.cvgenerator.service;

import com.cvgenerator.domain.entity.Project;

public interface ProjectService {

    void createProject(Long userCvId, Project project);

    void updateProject(Project project);

    void deleteProjectById(Long id);
}
