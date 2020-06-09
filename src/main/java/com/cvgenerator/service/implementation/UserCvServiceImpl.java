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

import java.util.Optional;

@Service
public class UserCvServiceImpl implements UserCvService {

    private final UserCvRepository userCvRepository;
    private final UserRepository userRepository;
    private final UserCvDtoConverter userCvDtoConverter;

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

    @Override
    public void updateUserCvBasicInfo(UserCvDto userCvDto) {
        Long id = userCvDto.getId();
        UserCv userCv = userCvRepository.findUserCvById(id)
                                                .orElseThrow();
        userCv.setName(userCvDto.getName());
        userCv.setTemplateName(userCvDto.getTemplateName());
        userCvRepository.save(userCv);
    }
}
