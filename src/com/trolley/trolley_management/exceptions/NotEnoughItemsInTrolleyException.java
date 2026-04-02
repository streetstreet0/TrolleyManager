package com.trolley.trolley_management.exceptions;

public class NotEnoughItemsInTrolleyException extends TrolleyManagementException {
    public NotEnoughItemsInTrolleyException(String itemName, int quantity) {
        super("There is less than " + quantity + "x " +  itemName + " in the trolley\nCannot remove more items than are in the trolley");
    }
}
