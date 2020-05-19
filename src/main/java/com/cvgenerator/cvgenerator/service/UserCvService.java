package com.cvgenerator.cvgenerator.service;

import com.cvgenerator.cvgenerator.domain.entity.UserCv;

public interface UserCvService {

    void saveUserCv(Long userId, UserCv userCv);
}
