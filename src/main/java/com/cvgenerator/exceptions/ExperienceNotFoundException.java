package com.cvgenerator.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExperienceNotFoundException extends RuntimeException {
    public ExperienceNotFoundException(String message) {
        super(message);
        log.error(message);
    }
}
