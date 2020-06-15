package com.cvgenerator.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TokenNotFoundException extends RuntimeException {
    public TokenNotFoundException(String message) {
        super(message);
        log.error(message);
    }
}
