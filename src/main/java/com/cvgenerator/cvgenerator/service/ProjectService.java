package com.cvgenerator.cvgenerator.service;

import com.cvgenerator.cvgenerator.domain.entity.Project;

public interface ProjectService {

    void saveProject(Long userCvId, Project project);

}
