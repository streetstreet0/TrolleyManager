package com.trolley.gui;

import com.trolley.exceptions.UnknownCommandException;

public enum UserCommand {
    ADD_ITEM,
    REMOVE_ITEM,
    SORT_TROLLEY,
    CANCEL_SORT,
    CLEAR_TROLLEY,
    DISPLAY_TROLLEY,
    CHECKOUT;

    public String displayMessage() {
        return userInput() + ": " + description();
    }

    public String description() {
        return switch (this) {
            case ADD_ITEM -> "add item to trolley";
            case REMOVE_ITEM -> "remove item from trolley";
            case SORT_TROLLEY -> "sort the trolley";
            case CANCEL_SORT -> "stop trying to sort the trolley";
            case CLEAR_TROLLEY -> "clear the trolley";
            case CHECKOUT -> "checkout and display the trolley";
            case DISPLAY_TROLLEY -> "display the trolley contents and running totals";
        };
    }

    public String userInput() {
        return switch (this) {
            case ADD_ITEM -> "ADD";
            case REMOVE_ITEM -> "REMOVE";
            case SORT_TROLLEY -> "SORT";
            case CANCEL_SORT -> "CANCEL";
            case CLEAR_TROLLEY -> "CLEAR";
            case CHECKOUT -> "CHECKOUT";
            case DISPLAY_TROLLEY -> "DISPLAY";
        };
    }

    public static UserCommand getUserCommand(String userInput) throws UnknownCommandException {
        return switch (userInput) {
            case "ADD" -> ADD_ITEM;
            case "REMOVE" -> REMOVE_ITEM;
            case "SORT" -> SORT_TROLLEY;
            case "CANCEL" -> CANCEL_SORT;
            case "CLEAR" -> CLEAR_TROLLEY;
            case "CHECKOUT" -> CHECKOUT;
            case "DISPLAY" -> DISPLAY_TROLLEY;
            default -> throw new UnknownCommandException(userInput);
        };
    }
}
