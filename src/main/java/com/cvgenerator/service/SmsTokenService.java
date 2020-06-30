package com.cvgenerator.service;

import com.cvgenerator.domain.entity.SmsToken;
import com.cvgenerator.domain.entity.Token;
import com.cvgenerator.domain.entity.User;

public interface SmsTokenService {

    SmsToken createSmsToken(User user);

    SmsToken findSmsTokenByValue(String value);
}
