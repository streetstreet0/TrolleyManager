package com.trolley.items;

public class Item {
    private String name;
    private double weight;
    private float price;

    public Item (String name, double weight, float price) {
        this.name = name;
        this.weight = weight;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public float getPrice() {
        return price;
    }

    public String getDisplayInfo() {
        return name + ": $" + price + ", " + weight + "g";
    }
}
