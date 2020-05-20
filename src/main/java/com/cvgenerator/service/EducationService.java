package com.cvgenerator.service;

import com.cvgenerator.domain.entity.Education;

public interface EducationService {

    void saveEducation(Long userCvId, Education education);
}
