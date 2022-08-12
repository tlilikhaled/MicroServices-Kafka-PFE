package com.khaled.commons.grade.exception;

public class MapperException extends RuntimeException {
    public MapperException(String msg, Throwable t) {
        super(msg, t);
    }

    public MapperException(String msg) {
        super(msg);
    }
}