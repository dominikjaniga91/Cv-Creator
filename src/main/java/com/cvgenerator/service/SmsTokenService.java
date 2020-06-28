package com.cvgenerator.service;

import com.cvgenerator.domain.entity.Token;

public interface SmsTokenService {

    Token createSmsToken(String email);

    Token findSmsTokenByValue(String value);
}
