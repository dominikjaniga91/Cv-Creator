package com.cvgenerator.service;

import com.cvgenerator.domain.dto.UserCvDto;
import com.cvgenerator.domain.entity.UserCv;

public interface UserCvService {

    void saveUserCv(Long userId, UserCvDto userCvDto);

    UserCvDto getUserCvById(Long id);
}
