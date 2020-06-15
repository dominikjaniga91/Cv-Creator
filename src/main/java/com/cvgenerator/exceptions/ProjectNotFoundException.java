package com.cvgenerator.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProjectNotFoundException extends RuntimeException {
    public ProjectNotFoundException(String message) {
        super(message);
        log.error(message);
    }
}
