package com.cvgenerator.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MissingServletRequestParameterException;

@Service
public class JsonProcessingService {

    public String processJsonString(String request, String value) throws MissingServletRequestParameterException {

        try {
            return new ObjectMapper().readTree(request).get(value).asText();
        }catch (JsonProcessingException | NullPointerException exception){
            throw new MissingServletRequestParameterException(value, value.getClass().toString());
        }
    }
}
