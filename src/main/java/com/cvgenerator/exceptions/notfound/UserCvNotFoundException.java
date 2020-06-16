package com.cvgenerator.exceptions.notfound;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserCvNotFoundException extends RuntimeException {
    public UserCvNotFoundException(String message) {
        super(message);
        log.error(message);
    }
}
