package com.cvgenerator.service.implementation;

import com.cvgenerator.config.Messages;
import com.cvgenerator.domain.entity.Interest;
import com.cvgenerator.domain.entity.UserCv;
import com.cvgenerator.exceptions.InterestNotFoundException;
import com.cvgenerator.exceptions.UserCvNotFoundException;
import com.cvgenerator.repository.InterestRepository;
import com.cvgenerator.repository.UserCvRepository;
import com.cvgenerator.service.InterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InterestServiceImpl implements InterestService {

    private final UserCvRepository userCvRepository;
    private final InterestRepository interestRepository;
    private final Messages messages;

    @Autowired
    public InterestServiceImpl(UserCvRepository userCvRepository,
                               InterestRepository interestRepository,
                               Messages messages) {
        this.userCvRepository = userCvRepository;
        this.interestRepository = interestRepository;
        this.messages = messages;
    }

    @Override
    public void createInterest(Long userCvId, Interest interest) {

        UserCv userCv = userCvRepository.findById(userCvId).orElseThrow(() -> new UserCvNotFoundException(messages.get("userCv.notfound")));
        interest.setUserCv(userCv);
        interestRepository.save(interest);
    }

    @Override
    public void updateInterest(Interest interest) {
        Long id = interest.getId();
        Interest foundedInterest = interestRepository.findById(id).orElseThrow(() -> new InterestNotFoundException(messages.get("interest.notfound")));
        foundedInterest.setName(interest.getName());
        interestRepository.save(foundedInterest);
    }

    @Override
    public void deleteInterestById(Long id) {
        try {
            interestRepository.deleteById(id);
        }catch (Exception ex){
            throw new InterestNotFoundException(messages.get("interest.notfound"));
        }
    }
}
