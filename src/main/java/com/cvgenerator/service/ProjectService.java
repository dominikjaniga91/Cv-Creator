package com.cvgenerator.service;

import com.cvgenerator.domain.entity.Project;

public interface ProjectService {

    void saveProject(Long userCvId, Project project);

}
