package com.cvgenerator.cvgenerator.service;

import com.cvgenerator.cvgenerator.domain.entity.Education;

public interface EducationService {

    void saveEducation(Long userCvId, Education education);
}
