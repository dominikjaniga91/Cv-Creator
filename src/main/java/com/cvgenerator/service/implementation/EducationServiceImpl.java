package com.cvgenerator.service.implementation;

import com.cvgenerator.config.Messages;
import com.cvgenerator.domain.entity.Education;
import com.cvgenerator.domain.entity.UserCv;
import com.cvgenerator.exceptions.notfound.EducationNotFoundException;
import com.cvgenerator.exceptions.notfound.UserCvNotFoundException;
import com.cvgenerator.repository.EducationRepository;
import com.cvgenerator.repository.UserCvRepository;
import com.cvgenerator.service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EducationServiceImpl implements EducationService {

    private final UserCvRepository userCvRepository;
    private final EducationRepository educationRepository;
    private final Messages messages;

    @Autowired
    public EducationServiceImpl(UserCvRepository userCvRepository,
                                EducationRepository educationRepository,
                                Messages messages) {
        this.userCvRepository = userCvRepository;
        this.educationRepository = educationRepository;
        this.messages = messages;
    }

    @Override
    public void createEducation(Long userCvId, Education education) {
        UserCv userCv = userCvRepository.findById(userCvId).orElseThrow(() -> new UserCvNotFoundException(messages.get("userCv.notfound")));
        education.setUserCv(userCv);
        educationRepository.save(education);
    }

    public void updateEducation(Education education) {
        Long id = education.getId();
        Education foundedEducation = educationRepository.findById(id).orElseThrow(() -> new EducationNotFoundException(messages.get("education.notfound")));

        foundedEducation.setId(education.getId());
        foundedEducation.setSchool(education.getSchool());
        foundedEducation.setCity(education.getCity());
        foundedEducation.setStartDate(education.getStartDate());
        foundedEducation.setFinishDate(education.getFinishDate());
        foundedEducation.setDegree(education.getDegree());
        foundedEducation.setSpecialization(education.getSpecialization());
        foundedEducation.setDescription(education.getDescription());

        educationRepository.save(foundedEducation);
    }

    public void deleteEducation(Long id) {
        try {
            educationRepository.deleteById(id);
        }catch (Exception ex){
            throw new EducationNotFoundException(messages.get("education.notfound"));
        }
    }
}
