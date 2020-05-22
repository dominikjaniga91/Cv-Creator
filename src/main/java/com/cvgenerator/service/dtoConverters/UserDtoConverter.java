package com.cvgenerator.service.dtoConverters;

import com.cvgenerator.domain.dto.UserDto;
import com.cvgenerator.domain.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDtoConverter implements EntityToDtoConverter<UserDto, User>{

    private ModelMapper modelMapper;

    @Autowired
    public UserDtoConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto convertToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }
}
