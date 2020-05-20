package com.cvgenerator.cvgenerator.service.implementation;

import com.cvgenerator.cvgenerator.domain.entity.Education;
import com.cvgenerator.cvgenerator.domain.entity.UserCv;
import com.cvgenerator.cvgenerator.repository.EducationRepository;
import com.cvgenerator.cvgenerator.repository.UserCvRepository;
import com.cvgenerator.cvgenerator.service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EducationServiceImpl implements EducationService {

    private UserCvRepository userCvRepository;
    private EducationRepository educationRepository;

    @Autowired
    public EducationServiceImpl(UserCvRepository userCvRepository, EducationRepository educationRepository) {
        this.userCvRepository = userCvRepository;
        this.educationRepository = educationRepository;
    }

    @Override
    public void saveEducation(Long userCvId, Education education) {
        UserCv userCv = userCvRepository.findById(userCvId).orElseThrow();
        education.setUserCv(userCv);
        educationRepository.save(education);
    }
}
