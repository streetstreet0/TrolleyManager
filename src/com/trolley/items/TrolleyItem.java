package com.trolley.items;

public class TrolleyItem {
    private Item item;
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

    public boolean isItem(Item item) {
        return this.item.getName().equals(item.getName());
    }

    public boolean isItem(String itemName) {
        return this.item.getName().equals(itemName);
    }

    public void addItem() {
        quantity++;
        runningWeight =+ item.getWeight();
        runningPrice =+ item.getPrice();
    }

    public void removeItem() throws RuntimeException {
        quantity--;
        runningWeight =- item.getWeight();
        runningPrice =- item.getPrice();
    }

    public boolean hasNone() {
        return quantity <= 0;
    }
}
