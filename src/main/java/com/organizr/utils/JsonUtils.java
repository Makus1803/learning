package com.organizr.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class JsonUtils {

    private static final Logger log = LoggerFactory.getLogger(JsonUtils.class);

    private JsonUtils(){}

    public static String toJson(Object object){
        ObjectMapper mapper = new ObjectMapper();
        try{
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("Failed to convert object to JSON string", e);
            return null;
        }
    }
}
