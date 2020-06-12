package com.cvgenerator.service.implementation;

import com.cvgenerator.domain.entity.Experience;
import com.cvgenerator.domain.entity.UserCv;
import com.cvgenerator.repository.ExperienceRepository;
import com.cvgenerator.repository.UserCvRepository;
import com.cvgenerator.service.ExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExperienceServiceImpl implements ExperienceService {

    private final UserCvRepository userCvRepository;
    private final ExperienceRepository experienceRepository;

    @Autowired
    public ExperienceServiceImpl(UserCvRepository userCvRepository, ExperienceRepository experienceRepository) {
        this.userCvRepository = userCvRepository;
        this.experienceRepository = experienceRepository;
    }

    @Override
    public void createExperience(Long userCvId, Experience experience) {
        UserCv userCv = userCvRepository.findById(userCvId).orElseThrow();
        experience.setUserCv(userCv);
        experienceRepository.save(experience);
    }
}
