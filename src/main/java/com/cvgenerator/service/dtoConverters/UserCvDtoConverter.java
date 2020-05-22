package com.cvgenerator.service.dtoConverters;

import com.cvgenerator.domain.dto.UserCvDto;
import com.cvgenerator.domain.entity.UserCv;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCvDtoConverter implements EntityToDtoConverter<UserCvDto, UserCv>, DtoToEntityConverter<UserCv, UserCvDto>{

    private ModelMapper modelMapper;

    @Autowired
    public UserCvDtoConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public UserCvDto convertToDto(UserCv userCv) {
        return  modelMapper.map(userCv, UserCvDto.class);
    }

    @Override
    public UserCv convertToEntity(UserCvDto userCvDto) {
        return modelMapper.map(userCvDto, UserCv.class);
    }
}
