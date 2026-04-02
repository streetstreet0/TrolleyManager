package com.trolley.exceptions;

public class ItemNotInTrolleyException extends RuntimeException {
    public ItemNotInTrolleyException(String itemName) {
        super(itemName + " is not in the trolley");
    }
}
