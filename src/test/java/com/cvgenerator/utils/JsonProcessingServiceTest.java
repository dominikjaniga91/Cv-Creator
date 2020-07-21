package com.cvgenerator.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.bind.MissingServletRequestParameterException;

@SpringBootTest
@ExtendWith(SpringExtension.class)

public class JsonProcessingServiceTest {

    @Autowired JsonProcessingService service;

    @Test
    @DisplayName("Should return appropriate password after pasre JsonNode")
    void shouldReturnAppropriatePassword_afterParseJsonNode() throws JsonProcessingException, MissingServletRequestParameterException {

        String json = "{\"password\":\"dominik123\"}";
        JsonNode jsonNode = new ObjectMapper().readTree(json);
        Assertions.assertEquals("dominik123", service.processJsonString(jsonNode, "password"));

    }

    @Test
    @DisplayName("Should throw an exception after parse non existing value")
    void shouldThrowAnException_afterParseNonExistingValue() throws JsonProcessingException {

        String json = "{\"newpassword\":\"dominik123\"}";
        JsonNode jsonNode = new ObjectMapper().readTree(json);
        MissingServletRequestParameterException exception = Assertions.assertThrows(MissingServletRequestParameterException.class,
                                                                                    () -> service.processJsonString(jsonNode, "password"));

        Assertions.assertEquals("Required class java.lang.String parameter 'password' is not present", exception.getMessage());

    }
}
