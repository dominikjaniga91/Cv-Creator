package com.cvgenerator.service;

import com.cvgenerator.domain.entity.Interest;

public interface InterestService {

    void createInterest(Long userCvId, Interest interest);

    void updateInterest(Interest interest);

    void deleteInterestById(Long id);
}
