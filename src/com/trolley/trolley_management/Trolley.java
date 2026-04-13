package com.trolley.trolley_management;

import com.trolley.trolley_management.exceptions.ItemNotFoundException;
import com.trolley.trolley_management.exceptions.ItemNotInTrolleyException;
import com.trolley.trolley_management.exceptions.NotEnoughItemsInTrolleyException;
import com.trolley.trolley_management.items.TrolleyItem;

import java.util.Stack;

public class Trolley {
    private final Store store;
    private final Stack<TrolleyItem> items;

    public Trolley(Store store) {
        this.store = store;
        items = new Stack<TrolleyItem>();
    }

    public boolean hasItems() {
        return !items.isEmpty();
    }

    public void checkItemInTrolley(String itemName) throws ItemNotInTrolleyException {
        for (TrolleyItem item : items) {
            if (item.isItem(itemName)) {
                return;
            }
        }
        throw new ItemNotInTrolleyException(itemName);
    }

    public void addItem(String itemName) throws ItemNotFoundException {
        addItem(itemName, 1);
    }

    public void addItem(String itemName, int quantity) throws ItemNotFoundException {
        store.checkItemIsAvailable(itemName);

        for (TrolleyItem item : items) {
            if (item.isItem(itemName)) {
                item.addItems(quantity);
                return;
            }
        }

        items.add(new TrolleyItem(store.getItem(itemName), quantity));
    }

    public void removeItem(String itemName) throws ItemNotFoundException, ItemNotInTrolleyException, NotEnoughItemsInTrolleyException {
        removeItem(itemName, 1);
    }

    public void removeItem(String itemName, int quantity) throws ItemNotFoundException, ItemNotInTrolleyException, NotEnoughItemsInTrolleyException {
        store.checkItemIsAvailable(itemName);

        for (TrolleyItem item : items) {
            if (item.isItem(itemName)) {
                item.removeItems(quantity);
                if (item.hasNone()) {
                    items.remove(item);
                }
                return;
            }
        }
        throw new ItemNotInTrolleyException(itemName);
    }

    public void clearTrolley() {
        items.clear();
    }

    public String displayItemsWithQuantity() {
        StringBuilder trolleyItemDisplay = new StringBuilder("Items:\n");
        for (TrolleyItem item : items) {
            trolleyItemDisplay.append("  ").append(item.itemWithQuantityString()).append("\n");
        }
        return trolleyItemDisplay.toString();
    }

    public String displayTrolley() {
        StringBuilder trolleyItemDisplay = new StringBuilder("Items:\n");
        double runningPrice = 0;
        double runningWeight = 0;
        for (TrolleyItem item : items) {
            trolleyItemDisplay.append("  ").append(item.itemFullDisplayString()).append("\n");
            runningPrice += item.getRunningPrice();
            runningWeight += item.getRunningWeight();
        }
        trolleyItemDisplay.append(runningTotalsDisplay(runningPrice, runningWeight));
        return trolleyItemDisplay.toString();
    }

    public String finalDisplayTrolley() {
        StringBuilder trolleyItemDisplay = new StringBuilder("Items:\n");
        double runningPrice = 0;
        double runningWeight = 0;
        for (int i = items.size()-1; i >= 0; i--) {
            TrolleyItem item = items.get(i);
            trolleyItemDisplay.append("  ").append(item.itemFullDisplayString()).append("\n");
            runningPrice += item.getRunningPrice();
            runningWeight += item.getRunningWeight();
        }
        trolleyItemDisplay.append(runningTotalsDisplay(runningPrice, runningWeight));
        return trolleyItemDisplay.toString();
    }

    private String runningTotalsDisplay(double runningPrice, double runningWeight) {
        StringBuilder runningTotalsDisplay = new StringBuilder();
        runningTotalsDisplay.append(String.format("Total price: $%.2f\n", runningPrice));
        if (runningWeight > 1000)
            runningTotalsDisplay.append(String.format("Total weight: %.2fkg\n", runningWeight/1000));
        else
            runningTotalsDisplay.append(String.format("Total weight: %.2fg\n", runningWeight));
        return runningTotalsDisplay.toString();
    }
}
