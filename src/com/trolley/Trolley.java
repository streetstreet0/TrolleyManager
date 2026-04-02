package com.trolley;

import com.trolley.exceptions.ItemNotFoundException;
import com.trolley.exceptions.ItemNotInTrolleyException;
import com.trolley.items.TrolleyItem;

import java.util.Stack;

public class Trolley {
    Store store;
    Stack<TrolleyItem> items;

    public Trolley() {
        items = new Stack<TrolleyItem>();
    }

    public boolean hasItems() {
        return !items.isEmpty();
    }

    public void addItem(String itemName) throws ItemNotFoundException {
        store.checkItemIsAvailable(itemName);

        for (TrolleyItem item : items) {
            if (item.isItem(itemName)) {
                item.addItem();
                return;
            }
        }

        items.add(new TrolleyItem(store.getItem(itemName)));
    }

    public void removeItem(String itemName) throws ItemNotFoundException, ItemNotInTrolleyException {
        store.checkItemIsAvailable(itemName);

        for (TrolleyItem item : items) {
            if (item.isItem(itemName)) {
                item.removeItem();
                if (item.hasNone()) {
                    items.remove(item);
                }
                return;
            }
        }
        throw new ItemNotInTrolleyException(itemName);
    }

}
