package com.cvgenerator.service;

import com.cvgenerator.domain.entity.Education;

public interface EducationService {

    void createEducation(Long userCvId, Education education);
}
