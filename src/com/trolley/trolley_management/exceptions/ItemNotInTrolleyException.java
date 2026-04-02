package com.trolley.trolley_management.exceptions;

public class ItemNotInTrolleyException extends TrolleyManagementException {
    public ItemNotInTrolleyException(String itemName) {
        super(itemName + " is not in the trolley");
    }
}
