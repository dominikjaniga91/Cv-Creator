package com.cvgenerator.cvgenerator.service.implementation;

import com.cvgenerator.cvgenerator.domain.entity.User;
import com.cvgenerator.cvgenerator.repository.UserRepository;
import com.cvgenerator.cvgenerator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public Optional<User> findUserById(Long id){
        return userRepository.findById(id);
    }
}
