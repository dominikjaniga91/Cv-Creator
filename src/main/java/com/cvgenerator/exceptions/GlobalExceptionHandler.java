package com.cvgenerator.exceptions;

import com.cvgenerator.exceptions.notfound.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private ErrorMessage getErrorMessage(HttpStatus code, Exception exception){

        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setErrorCode(code.value());
        errorMessage.setTimestamp(LocalDateTime.now());
        errorMessage.setErrorMessage(exception.getMessage());
        errorMessage.setDeveloperMessage(exception.getClass().toString());

        return errorMessage;
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorMessage errorMessage = getErrorMessage(status, ex);
        return new ResponseEntity<>(errorMessage, status);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> getUserNotFoundExceptionHandler(UserNotFoundException exception){

        ErrorMessage errorMessage = getErrorMessage(HttpStatus.NOT_FOUND, exception);
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserCvNotFoundException.class)
    public ResponseEntity<?> getUserCvNotFoundExceptionHandler(UserCvNotFoundException exception){

        ErrorMessage errorMessage = getErrorMessage(HttpStatus.NOT_FOUND, exception);
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TokenNotFoundException.class)
    public ResponseEntity<?> getTokenNotFoundExceptionHandler(TokenNotFoundException exception){

        ErrorMessage errorMessage = getErrorMessage(HttpStatus.NOT_FOUND, exception);
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SkillNotFoundException.class)
    public ResponseEntity<?> getSkillNotFoundExceptionHandler(SkillNotFoundException exception){

        ErrorMessage errorMessage = getErrorMessage(HttpStatus.NOT_FOUND, exception);
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProjectNotFoundException.class)
    public ResponseEntity<?> getProjectNotFoundExceptionHandler(ProjectNotFoundException exception){

        ErrorMessage errorMessage = getErrorMessage(HttpStatus.NOT_FOUND, exception);
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PersonalDataNotFoundException.class)
    public ResponseEntity<?> getPersonalDataNotFoundExceptionHandler(PersonalDataNotFoundException exception){

        ErrorMessage errorMessage = getErrorMessage(HttpStatus.NOT_FOUND, exception);
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(LanguageNotFoundException.class)
    public ResponseEntity<?> getLanguageNotFoundExceptionHandler(LanguageNotFoundException exception){

        ErrorMessage errorMessage = getErrorMessage(HttpStatus.NOT_FOUND, exception);
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ExperienceNotFoundException.class)
    public ResponseEntity<?> getExperienceNotFoundExceptionHandler(ExperienceNotFoundException exception){

        ErrorMessage errorMessage = getErrorMessage(HttpStatus.NOT_FOUND, exception);
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EducationNotFoundException.class)
    public ResponseEntity<?> getEducationNotFoundExceptionHandler(EducationNotFoundException exception){

        ErrorMessage errorMessage = getErrorMessage(HttpStatus.NOT_FOUND, exception);
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<?> getCourseNotFoundExceptionHandler(CourseNotFoundException exception){

        ErrorMessage errorMessage = getErrorMessage(HttpStatus.NOT_FOUND, exception);
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<?> getTokenExpiredExceptionHandler(TokenExpiredException exception){

        ErrorMessage errorMessage = getErrorMessage(HttpStatus.NOT_FOUND, exception);
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

}
