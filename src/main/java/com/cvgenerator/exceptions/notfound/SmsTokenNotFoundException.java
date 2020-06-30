package com.cvgenerator.exceptions.notfound;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SmsTokenNotFoundException extends RuntimeException {
    public SmsTokenNotFoundException(String message) {
        super(message);
        log.error(message);
    }
}
