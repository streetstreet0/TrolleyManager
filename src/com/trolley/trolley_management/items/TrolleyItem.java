package com.trolley.trolley_management.items;

import com.trolley.trolley_management.exceptions.NotEnoughItemsInTrolleyException;

public class TrolleyItem {
    private final Item item;
    private int quantity;
    private double runningWeight;
    private double runningPrice;

    public TrolleyItem(Item item) {
        this(item, 1);
    }

    public TrolleyItem(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
        runningPrice = item.getPrice() * quantity;
        runningWeight = item.getWeight() * quantity;
    }

    public double getRunningWeight() {
        return runningWeight;
    }

    public double getRunningPrice() {
        return runningPrice;
    }

    public boolean isItem(Item item) {
        return this.item.getName().equals(item.getName());
    }

    public boolean isItem(String itemName) {
        return this.item.getName().equals(itemName);
    }

    public void addItem() {
        addItems(1);
    }

    public void addItems(int addQuantity) {
        quantity += addQuantity;
        runningWeight += (item.getWeight() * addQuantity);
        runningPrice += (item.getPrice() * addQuantity);
    }

    public void removeItem() throws NotEnoughItemsInTrolleyException {
        removeItems(1);
    }

    public void removeItems(int removeQuantity) throws NotEnoughItemsInTrolleyException {
        if (removeQuantity > quantity)
            throw new NotEnoughItemsInTrolleyException(item.getName(), removeQuantity);
        quantity =- removeQuantity;
        runningWeight =- (item.getWeight() * removeQuantity);
        runningPrice =- (item.getPrice() * removeQuantity);
    }

    public boolean hasNone() {
        return quantity <= 0;
    }

    public String itemWithQuantityString() {
        return quantity + "x " + item.getName();
    }

    public String itemFullDisplayString() {
        return String.format(itemWithQuantityString() + ": $%.2f, %.2fg", runningPrice, runningWeight);
    }
}
