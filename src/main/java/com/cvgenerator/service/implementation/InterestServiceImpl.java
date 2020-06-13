package com.cvgenerator.service.implementation;

import com.cvgenerator.domain.entity.Interest;
import com.cvgenerator.domain.entity.UserCv;
import com.cvgenerator.repository.InterestRepository;
import com.cvgenerator.repository.UserCvRepository;
import com.cvgenerator.service.InterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InterestServiceImpl implements InterestService {

    private final UserCvRepository userCvRepository;
    private final InterestRepository interestRepository;

    @Autowired
    public InterestServiceImpl(UserCvRepository userCvRepository, InterestRepository interestRepository) {
        this.userCvRepository = userCvRepository;
        this.interestRepository = interestRepository;
    }

    @Override
    public void createInterest(Long userCvId, Interest interest) {

        UserCv userCv = userCvRepository.findById(userCvId).orElseThrow();
        interest.setUserCv(userCv);
        interestRepository.save(interest);
    }

    @Override
    public void updateInterest(Interest interest) {
        Long id = interest.getId();
        Interest foundedInterest = interestRepository.findById(id).orElseThrow();
        foundedInterest.setName(interest.getName());
        interestRepository.save(foundedInterest);
    }
}
