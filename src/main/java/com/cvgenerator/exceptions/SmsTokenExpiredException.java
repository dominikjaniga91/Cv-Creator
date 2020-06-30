package com.cvgenerator.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SmsTokenExpiredException extends RuntimeException {
    public SmsTokenExpiredException(String message) {
        super(message);
        log.error(message);
    }
}
