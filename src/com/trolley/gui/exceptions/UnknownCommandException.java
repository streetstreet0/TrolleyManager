package com.trolley.gui.exceptions;

public class UnknownCommandException extends UserCommandException {
    public UnknownCommandException(String userInput) {
        super("Unknown command: " + userInput);
    }
}
