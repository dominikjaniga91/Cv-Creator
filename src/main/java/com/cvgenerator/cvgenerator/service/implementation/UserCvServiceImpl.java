package com.cvgenerator.cvgenerator.service.implementation;

import com.cvgenerator.cvgenerator.domain.entity.User;
import com.cvgenerator.cvgenerator.domain.entity.UserCv;
import com.cvgenerator.cvgenerator.repository.UserCvRepository;
import com.cvgenerator.cvgenerator.repository.UserRepository;
import com.cvgenerator.cvgenerator.service.UserCvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCvServiceImpl implements UserCvService {

    private UserCvRepository userCvRepository;
    private UserRepository userRepository;

    @Autowired
    public UserCvServiceImpl(UserCvRepository userCvRepository, UserRepository userRepository) {
        this.userCvRepository = userCvRepository;
        this.userRepository = userRepository;
    }

    @Override
    public UserCv saveUserCv(Long userId, UserCv userCv) {

        User user = userRepository.findById(userId).orElseThrow();
        UserCv newUserCv = userCvRepository.save(userCv);
        newUserCv.setUser(user);
        return newUserCv;
    }
}
