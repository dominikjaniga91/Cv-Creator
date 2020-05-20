package com.cvgenerator.cvgenerator.service.implementation;

import com.cvgenerator.cvgenerator.domain.entity.Experience;
import com.cvgenerator.cvgenerator.domain.entity.UserCv;
import com.cvgenerator.cvgenerator.repository.ExperienceRepository;
import com.cvgenerator.cvgenerator.repository.UserCvRepository;
import com.cvgenerator.cvgenerator.service.ExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExperienceServiceImpl implements ExperienceService {

    private UserCvRepository userCvRepository;
    private ExperienceRepository experienceRepository;

    @Autowired
    public ExperienceServiceImpl(UserCvRepository userCvRepository, ExperienceRepository experienceRepository) {
        this.userCvRepository = userCvRepository;
        this.experienceRepository = experienceRepository;
    }

    @Override
    public void saveExperience(Long userCvId, Experience experience) {
        UserCv userCv = userCvRepository.findById(userCvId).orElseThrow();
        experience.setUserCv(userCv);
        experienceRepository.save(experience);
    }
}
