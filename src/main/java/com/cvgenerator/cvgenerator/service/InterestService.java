package com.cvgenerator.cvgenerator.service;

import com.cvgenerator.cvgenerator.domain.entity.Interest;

public interface InterestService {

    void saveInterests(Long userCvId, Interest interest);
}
