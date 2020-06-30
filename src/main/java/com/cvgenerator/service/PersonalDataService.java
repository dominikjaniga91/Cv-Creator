package com.cvgenerator.service;

import com.cvgenerator.domain.entity.PersonalData;

public interface PersonalDataService {

    void createPersonalData(Long userCvId, PersonalData personalData);

    void updatePersonalData(PersonalData personalData);

    void deletePersonalDataById(Long id);
}
