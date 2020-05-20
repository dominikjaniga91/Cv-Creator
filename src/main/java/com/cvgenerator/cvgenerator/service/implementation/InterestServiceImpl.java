package com.cvgenerator.cvgenerator.service.implementation;

import com.cvgenerator.cvgenerator.domain.entity.Interest;
import com.cvgenerator.cvgenerator.domain.entity.UserCv;
import com.cvgenerator.cvgenerator.repository.InterestRepository;
import com.cvgenerator.cvgenerator.repository.UserCvRepository;
import com.cvgenerator.cvgenerator.service.InterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InterestServiceImpl implements InterestService {

    private UserCvRepository userCvRepository;
    private InterestRepository interestRepository;

    @Autowired
    public InterestServiceImpl(UserCvRepository userCvRepository, InterestRepository interestRepository) {
        this.userCvRepository = userCvRepository;
        this.interestRepository = interestRepository;
    }

    @Override
    public void saveInterests(Long userCvId, Interest interest) {

        UserCv userCv = userCvRepository.findById(userCvId).orElseThrow();
        interest.setUserCv(userCv);
        interestRepository.save(interest);
    }
}
