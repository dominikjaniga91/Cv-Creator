package com.cvgenerator.cvgenerator.service.implementation;

import com.cvgenerator.cvgenerator.domain.entity.UserCv;
import com.cvgenerator.cvgenerator.repository.UserCvRepository;
import com.cvgenerator.cvgenerator.service.UserCvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCvServiceImpl implements UserCvService {

    private UserCvRepository userCvRepository;

    @Autowired
    public UserCvServiceImpl(UserCvRepository userCvRepository) {
        this.userCvRepository = userCvRepository;
    }

    @Override
    public UserCv saveUserCv(UserCv userCv) {

        return userCvRepository.save(userCv);
    }
}
