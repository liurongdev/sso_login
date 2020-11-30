package com.login.sso_login.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JackSonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String convertObjectToString(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

    public static Object convertStringToObject(String string, Class objectClass) throws IOException {
        return objectMapper.readValue(string,objectClass);
    }
}
