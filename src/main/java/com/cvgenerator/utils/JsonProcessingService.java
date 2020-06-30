package com.cvgenerator.utils;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MissingServletRequestParameterException;

@Service
public class JsonProcessingService {

    public String processJsonString(JsonNode request, String value) throws MissingServletRequestParameterException {

        try {
            return request.get(value).asText();
        }catch (NullPointerException exception){
            throw new MissingServletRequestParameterException(value, value.getClass().toString());
        }
    }
}
