package com.cvgenerator.service.implementation;

import com.cvgenerator.domain.entity.PersonalData;
import com.cvgenerator.domain.entity.UserCv;
import com.cvgenerator.repository.PersonalDataRepository;
import com.cvgenerator.repository.UserCvRepository;
import com.cvgenerator.service.PersonalDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonalDataServiceImpl implements PersonalDataService {

    private final UserCvRepository userCvRepository;
    private final PersonalDataRepository dataRepository;

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
