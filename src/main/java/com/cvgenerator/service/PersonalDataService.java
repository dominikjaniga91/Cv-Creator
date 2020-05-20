package com.cvgenerator.service;

import com.cvgenerator.domain.entity.PersonalData;

public interface PersonalDataService {

    void savePersonalData(Long userCvId, PersonalData personalData);
}
