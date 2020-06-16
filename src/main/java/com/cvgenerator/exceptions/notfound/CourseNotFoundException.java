package com.cvgenerator.exceptions.notfound;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException(String message) {
        super(message);
        log.error(message);
    }
}
