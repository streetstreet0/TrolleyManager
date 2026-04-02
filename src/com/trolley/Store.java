package com.trolley;

import com.trolley.exceptions.ItemNotFoundException;
import com.trolley.items.Item;

import java.util.HashMap;

public class Store {
    private HashMap<String, Item> availableItems;

    public Store () {
        Item[] defaultItems = defaultItems();
        this(defaultItems);
    }

    public Store (Item[] availableItems) {
        this.availableItems = new HashMap<String, Item>();
        for (Item item : availableItems) {
            addAvailableItem(item);
        }
    }

    public boolean hasItem(String itemName) {
        return availableItems.get(itemName) != null;
    }

    public void checkItemIsAvailable(String itemName) throws ItemNotFoundException {
        if (!hasItem(itemName))
            throw new ItemNotFoundException(itemName);
    }

    public Item getItem(String itemName) throws ItemNotFoundException {
        checkItemIsAvailable(itemName);

        return availableItems.get(itemName);
    }

    private static Item[] defaultItems() {
        Item[] defaultItems = new Item[3];
        defaultItems[0] = new Item("item1", 12, 3);
        defaultItems[1] = new Item("item2", 6, 7);
        defaultItems[2] = new Item("item2", 7.5, 12.99F);
        return defaultItems;
    }

    private void addAvailableItem(Item item) {
        availableItems.put(item.getName(), item);
    }
}
