package com.cvgenerator.service;

import com.cvgenerator.domain.entity.Interest;

public interface InterestService {

    void saveInterests(Long userCvId, Interest interest);
}
