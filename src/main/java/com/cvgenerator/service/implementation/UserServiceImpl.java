package com.cvgenerator.service.implementation;

import com.cvgenerator.domain.dto.UserDto;
import com.cvgenerator.domain.entity.User;
import com.cvgenerator.domain.entity.UserCv;
import com.cvgenerator.repository.UserCvRepository;
import com.cvgenerator.repository.UserRepository;
import com.cvgenerator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserCvRepository userCvRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           UserCvRepository userCvRepository,
                           PasswordEncoder passwordEncoder){

        this.userRepository = userRepository;
        this.userCvRepository = userCvRepository;
        this.passwordEncoder = passwordEncoder;
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

    public void updateUser(UserDto userDto) {

        User user = userRepository.findUserByEmail(userDto.getEmail()).orElseThrow();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        if(user.getPassword() != null){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
    }

    public void deleteUserAccount(Long userId, String password){

        User user = userRepository.findById(userId).orElseThrow();

        if(user.getPassword().equals(password)){
            userRepository.delete(user);
        }

    }
}
