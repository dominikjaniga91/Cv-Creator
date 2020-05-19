package com.cvgenerator.cvgenerator.service;

import com.cvgenerator.cvgenerator.domain.entity.UserCv;

public interface UserCvService {

    UserCv saveUserCv(Long userId, UserCv userCv);
}
