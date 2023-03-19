package com.example.tstrestaurant.utils;

import com.example.tstrestaurant.error.IllegalRequestDataException;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidationUtil {



    public static <T> T checkExisted(T obj, Long id) {
        if (obj == null) {
            throw new IllegalRequestDataException("Entity with id=" + id + " not found");
        }
        return obj;
    }

    public static void checkModification(int count, Long id) {
        if (count == 0) {
            throw new IllegalRequestDataException("Entity with id=" + id + " not found");
        }
    }
}
