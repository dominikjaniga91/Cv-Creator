package com.cvgenerator.service;

import com.cvgenerator.domain.entity.Experience;

public interface ExperienceService {

    void saveExperience(Long userCvId, Experience experience);
}
