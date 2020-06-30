package com.cvgenerator.exceptions.notfound;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SummaryNotFoundException extends RuntimeException {
    public SummaryNotFoundException(String message) {
        super(message);
        log.error(message);
    }
}
