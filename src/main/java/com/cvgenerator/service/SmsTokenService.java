package com.cvgenerator.service;

import com.cvgenerator.domain.entity.SmsToken;
import com.cvgenerator.domain.entity.Token;

public interface SmsTokenService {

    SmsToken createSmsToken(String email);

    SmsToken findSmsTokenByValue(String value);
}
