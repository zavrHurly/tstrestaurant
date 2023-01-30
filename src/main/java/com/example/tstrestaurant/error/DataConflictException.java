package com.example.tstrestaurant.error;

public class DataConflictException extends RuntimeException {
    public DataConflictException(String msg) {
        super(msg);
    }
}
