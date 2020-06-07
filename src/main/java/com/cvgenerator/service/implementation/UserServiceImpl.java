package com.cvgenerator.service.implementation;

import com.cvgenerator.domain.dto.UserCvShortDto;
import com.cvgenerator.domain.dto.UserDto;
import com.cvgenerator.domain.entity.User;
import com.cvgenerator.domain.entity.UserCv;
import com.cvgenerator.repository.UserCvRepository;
import com.cvgenerator.repository.UserRepository;
import com.cvgenerator.service.UserService;
import com.cvgenerator.service.dtoConverters.UserCvShortDtoConverter;
import com.cvgenerator.service.dtoConverters.UserDtoConverter;
import com.cvgenerator.utils.service.implementation.MailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserCvRepository userCvRepository;
    private PasswordEncoder passwordEncoder;
    private UserDtoConverter userDtoConverter;
    private UserCvShortDtoConverter userCvShortDtoConverter;
    private final MailServiceImpl mailService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           UserCvRepository userCvRepository,
                           PasswordEncoder passwordEncoder,
                           UserDtoConverter userDtoConverter,
                           UserCvShortDtoConverter userCvShortDtoConverter,
                           MailServiceImpl mailService){

        this.userRepository = userRepository;
        this.userCvRepository = userCvRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDtoConverter = userDtoConverter;
        this.userCvShortDtoConverter = userCvShortDtoConverter;
        this.mailService = mailService;
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRegistration(LocalDateTime.now());
        userRepository.save(user);
        mailService.sendConfirmationEmail(user);
    }

    @Override
    public UserDto findUserById(Long id){
        User user = userRepository.findById(id).orElseThrow();
        return userDtoConverter.convertToDto(user);
    }

    @Override
    public List<UserCvShortDto> getListOfUserCv(Long id) {

        User user = userRepository.findById(id).orElseThrow();
        List<UserCv> userCv = userCvRepository.getAllByUser(user).orElseThrow();
        return userCvShortDtoConverter.convertListToDto(userCv);
    }

    @Override
    public void updateUser(User user) {

        User foundedUser = userRepository.findUserByEmail(user.getEmail()).orElseThrow();
        foundedUser.setFirstName(user.getFirstName());
        foundedUser.setLastName(user.getLastName());
        foundedUser.setEmail(user.getEmail());
        if(user.getPassword() != null){
            foundedUser.setPassword(passwordEncoder.encode(user.getPassword()));
        }
    }

    @Override
    public void deleteUserAccount(Long userId, String password){
        User user = userRepository.findById(userId).orElseThrow();
        if(user.getPassword().equals(password)){
            userRepository.delete(user);
        }

    }
}
