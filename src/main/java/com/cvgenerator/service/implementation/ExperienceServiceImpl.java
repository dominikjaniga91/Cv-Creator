package com.cvgenerator.service.implementation;

import com.cvgenerator.config.Messages;
import com.cvgenerator.domain.entity.Experience;
import com.cvgenerator.domain.entity.UserCv;
import com.cvgenerator.exceptions.ExperienceNotFoundException;
import com.cvgenerator.exceptions.UserCvNotFoundException;
import com.cvgenerator.repository.ExperienceRepository;
import com.cvgenerator.repository.UserCvRepository;
import com.cvgenerator.service.ExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExperienceServiceImpl implements ExperienceService {

    private final UserCvRepository userCvRepository;
    private final ExperienceRepository experienceRepository;
    private final Messages messages;

    @Autowired
    public ExperienceServiceImpl(UserCvRepository userCvRepository,
                                 ExperienceRepository experienceRepository,
                                 Messages messages) {
        this.userCvRepository = userCvRepository;
        this.experienceRepository = experienceRepository;
        this.messages = messages;
    }

    @Override
    public void createExperience(Long userCvId, Experience experience) {
        UserCv userCv = userCvRepository.findById(userCvId).orElseThrow(() -> new UserCvNotFoundException(messages.get("userCv.notfound")));
        experience.setUserCv(userCv);
        experienceRepository.save(experience);
    }

    @Override
    public void updateExperience(Experience experience) {

        Long id = experience.getId();
        Experience foundedExperience = experienceRepository.findById(id).orElseThrow(() -> new ExperienceNotFoundException(messages.get("experience.notfound")));

        foundedExperience.setId(experience.getId());
        foundedExperience.setCompany(experience.getCompany());
        foundedExperience.setCity(experience.getCity());
        foundedExperience.setPosition(experience.getPosition());
        foundedExperience.setStartDate(experience.getStartDate());
        foundedExperience.setFinishDate(experience.getFinishDate());
        foundedExperience.setDescription(experience.getDescription());

        experienceRepository.save(foundedExperience);
    }

    @Override
    public void deleteExperienceById(Long id) {
        try{
            experienceRepository.deleteById(id);
        }catch (Exception ex){
            throw new ExperienceNotFoundException(messages.get("experience.notfound"));
        }
    }
}
