package com.cvgenerator.utils.service;

import com.cvgenerator.domain.entity.SmsToken;

public interface SmsService {

    void sendSms(String email) throws Throwable;
}
