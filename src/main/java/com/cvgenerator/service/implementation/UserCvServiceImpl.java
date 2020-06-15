package com.cvgenerator.service.implementation;

import com.cvgenerator.config.Messages;
import com.cvgenerator.domain.dto.UserCvDto;
import com.cvgenerator.domain.entity.User;
import com.cvgenerator.domain.entity.UserCv;
import com.cvgenerator.exceptions.UserCvNotFoundException;
import com.cvgenerator.exceptions.UserNotFoundException;
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
    private final Messages messages;

    @Autowired
    public UserCvServiceImpl(UserCvRepository userCvRepository,
                             UserRepository userRepository,
                             UserCvDtoConverter userCvDtoConverter,
                             Messages messages) {

        this.userCvRepository = userCvRepository;
        this.userRepository = userRepository;
        this.userCvDtoConverter = userCvDtoConverter;
        this.messages = messages;
    }

    @Override
    public void saveUserCv(Long userId, UserCvDto userCvDto) {

        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(messages.get("user.notfound")));
        UserCv userCv = userCvDtoConverter.convertToEntity(userCvDto);
        userCv.setUser(user);
        userCvRepository.save(userCv);
    }

    @Override
    public UserCvDto getUserCvById(Long id) {
        UserCv userCv = userCvRepository.findUserCvById(id).orElseThrow(() -> new UserCvNotFoundException(messages.get("userCv.notfound")));
        return userCvDtoConverter.convertToDto(userCv);
    }

    @Override
    public void deleteCvById(Long id) {
        try{
            userCvRepository.deleteById(id);
        }catch (Exception ex){
            throw new UserCvNotFoundException(messages.get("userCv.notfound"));
        }

    }

    @Override
    public void updateUserCvBasicInfo(UserCvDto userCvDto) {
        Long id = userCvDto.getId();
        UserCv userCv = userCvRepository.findUserCvById(id).orElseThrow(() -> new UserCvNotFoundException(messages.get("userCv.notfound")));
        userCv.setName(userCvDto.getName());
        userCv.setTemplateName(userCvDto.getTemplateName());
        userCvRepository.save(userCv);
    }
}
