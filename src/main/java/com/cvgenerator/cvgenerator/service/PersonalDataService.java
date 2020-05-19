package com.cvgenerator.cvgenerator.service;

import com.cvgenerator.cvgenerator.domain.entity.PersonalData;

public interface PersonalDataService {

    void savePersonalData(Long userCvId, PersonalData personalData);
}
