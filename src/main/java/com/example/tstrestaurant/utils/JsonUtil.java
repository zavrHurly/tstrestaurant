package com.example.tstrestaurant.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.example.tstrestaurant.web.json.JacksonObjectMapper.getMapper;

@UtilityClass
public class JsonUtil {
    public static <T> List<T> readValues(String json, Class<T> clazz) {
        ObjectReader reader = getMapper().readerFor(clazz);
        try {
            return reader.<T>readValues(json).readAll();
        } catch (IOException e) {
            throw new IllegalArgumentException("Invalid read array from JSON:\n'" + json + "'", e);
        }
    }

    public static <T> T readValue(String json, Class<T> clazz) {
        try {
            return getMapper().readValue(json, clazz);
        } catch (IOException e) {
            throw new IllegalArgumentException("Invalid read from JSON:\n'" + json + "'", e);
        }
    }

    public static <T> String writeValueFromList(List list) {
        try {
            List<String> back = new ArrayList<>();
            for(Object obj: list) {
                back.add(getMapper().writeValueAsString(obj));
            }
            return getMapper().writeValueAsString(back);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Invalid write to JSON:\n'" + list + "'", e);
        }
    }

    public static <T> String writeValue(T obj) {
        try {
            return getMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Invalid write to JSON:\n'" + obj + "'", e);
        }
    }

    public static <T> String writeIgnoreProps(Collection<T> collection, String... ignoreProps) {
        List<Map<String, Object>> list = collection.stream()
                .map(e -> getAsMapWithIgnore(e, ignoreProps))
                .collect(Collectors.toList());
        return writeValue(list);
    }

    public static <T> String writeIgnoreProps(T obj, String... ignoreProps) {
        Map<String, Object> map = getAsMapWithIgnore(obj, ignoreProps);
        return writeValue(map);
    }

    private static <T> Map<String, Object> getAsMapWithIgnore(T obj, String[] ignoreProps) {
        Map<String, Object> map = getMapper().convertValue(obj, new TypeReference<Map<String, Object>>() {});
        for (String prop : ignoreProps) {
            map.remove(prop);
        }
        return map;
    }

    public static <T> String writeAdditionProps(T obj, String addName, Object addValue) {
        return writeAdditionProps(obj, Map.of(addName, addValue));
    }

    public static <T> String writeAdditionProps(T obj, Map<String, Object> addProps) {
        Map<String, Object> map = getMapper().convertValue(obj, new TypeReference<>() {});
        map.putAll(addProps);
        return writeValue(map);
    }
}
