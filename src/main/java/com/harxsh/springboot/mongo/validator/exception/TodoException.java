package com.harxsh.springboot.mongo.validator.exception;

public class TodoException extends Exception {

    public TodoException(String message) {
        super(message);
    }

    public static String NotFoundException(String id) {
        return "Todo with id " + id + " not found.";
    }

    public static String AlreadyExistsException(String title) {
        return "Todo with title \"" + title + "\" already exists.";
    }
}
