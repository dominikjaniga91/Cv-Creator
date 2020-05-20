package com.cvgenerator.service;

import com.cvgenerator.domain.entity.UserCv;

public interface UserCvService {

    void saveUserCv(Long userId, UserCv userCv);
}
