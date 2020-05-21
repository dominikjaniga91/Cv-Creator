package com.cvgenerator.service.implementation;

import com.cvgenerator.domain.entity.User;
import com.cvgenerator.domain.entity.UserCv;
import com.cvgenerator.repository.UserCvRepository;
import com.cvgenerator.repository.UserRepository;
import com.cvgenerator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserCvRepository userCvRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserCvRepository userCvRepository) {
        this.userRepository = userRepository;
        this.userCvRepository = userCvRepository;
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User findUserById(Long id){
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public Optional<List<UserCv>> getListOfUserCv(Long id) {

        User user = userRepository.findById(id).orElseThrow();
        Optional<List<UserCv>> userCv = userCvRepository.getAllByUser(user);

        return userCv;
    }
}
