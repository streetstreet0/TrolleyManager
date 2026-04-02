package com.trolley.exceptions;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(String itemName) {
        super(itemName + " is not an available item");
    }
}
