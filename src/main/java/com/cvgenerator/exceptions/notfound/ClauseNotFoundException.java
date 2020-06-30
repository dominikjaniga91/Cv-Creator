package com.cvgenerator.exceptions.notfound;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClauseNotFoundException extends RuntimeException {
    public ClauseNotFoundException(String message) {
        super(message);
        log.error(message);
    }
}
