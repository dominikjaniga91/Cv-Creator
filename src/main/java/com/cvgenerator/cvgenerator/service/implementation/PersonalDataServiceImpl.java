package com.cvgenerator.cvgenerator.service.implementation;

import com.cvgenerator.cvgenerator.domain.entity.PersonalData;
import com.cvgenerator.cvgenerator.domain.entity.UserCv;
import com.cvgenerator.cvgenerator.repository.PersonalDataRepository;
import com.cvgenerator.cvgenerator.repository.UserCvRepository;
import com.cvgenerator.cvgenerator.service.PersonalDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonalDataServiceImpl implements PersonalDataService {

    private UserCvRepository userCvRepository;
    private PersonalDataRepository dataRepository;

    @Autowired
    public PersonalDataServiceImpl(UserCvRepository userCvRepository, PersonalDataRepository dataRepository) {
        this.userCvRepository = userCvRepository;
        this.dataRepository = dataRepository;
    }

    @Override
    public void savePersonalData(Long userCvId, PersonalData personalData) {
        UserCv userCv = userCvRepository.findById(userCvId).orElseThrow();
        personalData.setUserCv(userCv);
        dataRepository.save(personalData);
    }
}
