package com.cvgenerator.exceptions.notfound;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PersonalDataNotFoundException extends RuntimeException {
    public PersonalDataNotFoundException(String message) {
        super(message);
        log.error(message);
    }
}
