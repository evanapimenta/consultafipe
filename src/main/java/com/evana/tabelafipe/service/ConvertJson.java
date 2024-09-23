package com.evana.tabelafipe.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.Arrays;
import java.util.List;

public class ConvertJson implements IConvertJson{
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T convertJson(String json, Class<T> tClass) {
        try {
            return mapper.readValue(json, tClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> List<T> convertJsonToArray(String jsonArray, Class<T[]> tClass) {
        try {
            T[] array = mapper.readValue(jsonArray, tClass);
            return Arrays.asList(array);
        } catch (Exception e) {
            throw new RuntimeException("Could not deserialize Json Array to list");
        }
    }
}
