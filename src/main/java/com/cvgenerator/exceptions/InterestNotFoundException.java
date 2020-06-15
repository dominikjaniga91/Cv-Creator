package com.cvgenerator.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InterestNotFoundException extends RuntimeException {
    public InterestNotFoundException(String message) {
        super(message);
        log.error(message);
    }
}
