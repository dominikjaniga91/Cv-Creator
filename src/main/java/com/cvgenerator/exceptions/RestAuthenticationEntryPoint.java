package com.cvgenerator.exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@Slf4j
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException exception) {

        ErrorMessage errorMessage = getErrorMessage(exception);

        try {
            String message = new ObjectMapper().writeValueAsString(errorMessage);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getOutputStream().print(message);

        }catch (IOException ex){
            log.error(ex.getMessage());
        }
    }

    private ErrorMessage getErrorMessage(Exception ex){

        return new ErrorMessage.Builder()
                .setErrorCode(HttpStatus.UNAUTHORIZED.value())
                .setTimestamp(LocalDateTime.now())
                .setErrorMessage(ex.getMessage())
                .setDeveloperMessage(ex.getClass().toString())
                .buildErrorMessage();
    }
}
