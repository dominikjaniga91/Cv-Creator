package com.cvgenerator.service.implementation;

import com.cvgenerator.domain.dto.UserCvDto;
import com.cvgenerator.domain.entity.User;
import com.cvgenerator.domain.entity.UserCv;
import com.cvgenerator.repository.UserCvRepository;
import com.cvgenerator.repository.UserRepository;
import com.cvgenerator.service.UserCvService;
import com.cvgenerator.service.dtoConverters.UserCvDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCvServiceImpl implements UserCvService {

    private UserCvRepository userCvRepository;
    private UserRepository userRepository;
    private UserCvDtoConverter userCvDtoConverter;

    @Autowired
    public UserCvServiceImpl(UserCvRepository userCvRepository,
                             UserRepository userRepository,
                             UserCvDtoConverter userCvDtoConverter) {

        this.userCvRepository = userCvRepository;
        this.userRepository = userRepository;
        this.userCvDtoConverter = userCvDtoConverter;
    }

    @Override
    public void saveUserCv(Long userId, UserCvDto userCvDto) {

        User user = userRepository.findById(userId).orElseThrow();
        UserCv userCv = userCvDtoConverter.convertToEntity(userCvDto);
        userCv.setUser(user);
        userCvRepository.save(userCv);
    }

    @Override
    public UserCvDto getUserCvById(Long id) {
        UserCv userCv = userCvRepository.findUserCvById(id).orElseThrow();
        return userCvDtoConverter.convertToDto(userCv);
    }

    @Override
    public void deleteCvById(Long id) {
        userCvRepository.deleteById(id);
    }
}
