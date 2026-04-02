package com.trolley.exceptions;

public class UnknownCommandException extends RuntimeException {
    public UnknownCommandException(String userInput) {
        super("Unknown command: " + userInput);
    }
}
