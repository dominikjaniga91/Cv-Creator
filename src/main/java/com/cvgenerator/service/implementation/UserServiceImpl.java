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
    private UserDtoConverter userDtoConverter;
    private UserCvShortDtoConverter userCvShortDtoConverter;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           UserCvRepository userCvRepository,
                           PasswordEncoder passwordEncoder,
                           UserDtoConverter userDtoConverter,
                           UserCvShortDtoConverter userCvShortDtoConverter){

        this.userRepository = userRepository;
        this.userCvRepository = userCvRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDtoConverter = userDtoConverter;
        this.userCvShortDtoConverter = userCvShortDtoConverter;
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
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
    public void updateUser(UserDto userDto) {

        User user = userRepository.findUserByEmail(userDto.getEmail()).orElseThrow();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        if(user.getPassword() != null){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
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
