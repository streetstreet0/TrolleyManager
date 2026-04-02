package com.trolley.trolley_management.exceptions;

public class ItemNotFoundException extends TrolleyManagementException {
    public ItemNotFoundException(String itemName) {
        super(itemName + " is not an available item");
    }
}
