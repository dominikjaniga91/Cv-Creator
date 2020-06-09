package com.cvgenerator.service.implementation;

import com.cvgenerator.domain.entity.Education;
import com.cvgenerator.domain.entity.UserCv;
import com.cvgenerator.repository.EducationRepository;
import com.cvgenerator.repository.UserCvRepository;
import com.cvgenerator.service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EducationServiceImpl implements EducationService {

    private final UserCvRepository userCvRepository;
    private final EducationRepository educationRepository;

    @Autowired
    public EducationServiceImpl(UserCvRepository userCvRepository, EducationRepository educationRepository) {
        this.userCvRepository = userCvRepository;
        this.educationRepository = educationRepository;
    }

    @Override
    public void createEducation(Long userCvId, Education education) {
        UserCv userCv = userCvRepository.findById(userCvId).orElseThrow();
        education.setUserCv(userCv);
        educationRepository.save(education);
    }

    public void updateEducation(Education education) {
        Long id = education.getId();
        Education foundedEducation = educationRepository.findById(id).orElseThrow();

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
}
