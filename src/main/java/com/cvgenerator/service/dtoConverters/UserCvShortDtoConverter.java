package com.cvgenerator.service.dtoConverters;

import com.cvgenerator.domain.dto.UserCvShortDto;
import com.cvgenerator.domain.entity.UserCv;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCvShortDtoConverter implements EntityToDtoConverter<UserCvShortDto, UserCv>, DtoToEntityConverter<UserCv, UserCvShortDto>{

    private ModelMapper modelMapper;

    @Autowired
    public UserCvShortDtoConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public UserCvShortDto convertToDto(UserCv userCv) {
        return  modelMapper.map(userCv, UserCvShortDto.class);
    }

    @Override
    public UserCv convertToEntity(UserCvShortDto userCvShortDto) {
        return modelMapper.map(userCvShortDto, UserCv.class);
    }
}
