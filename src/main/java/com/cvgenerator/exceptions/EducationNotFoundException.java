package com.cvgenerator.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EducationNotFoundException extends RuntimeException {
    public EducationNotFoundException(String message) {
        super(message);
        log.error(message);
    }
}
