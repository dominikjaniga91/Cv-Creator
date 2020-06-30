package com.cvgenerator.service;

import com.cvgenerator.domain.entity.Education;

public interface EducationService {

    void createEducation(Long userCvId, Education education);

    void updateEducation(Education education);

    void deleteEducationById(Long id);
}
