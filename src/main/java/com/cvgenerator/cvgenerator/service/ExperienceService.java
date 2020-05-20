package com.cvgenerator.cvgenerator.service;

import com.cvgenerator.cvgenerator.domain.entity.Experience;

public interface ExperienceService {

    void saveExperience(Long userCvId, Experience experience);
}
