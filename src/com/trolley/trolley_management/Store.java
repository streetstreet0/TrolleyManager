package com.trolley.trolley_management;

import com.trolley.trolley_management.exceptions.ItemNotFoundException;
import com.trolley.trolley_management.items.Item;

import java.util.HashMap;

public class Store {
    private HashMap<String, Item> availableItems;

    public Store (Item[] availableItems) {
        this.availableItems = new HashMap<String, Item>();
        for (Item item : availableItems) {
            addAvailableItem(item);
        }
    }

    public String displayStoreItems() {
        StringBuilder storeItemDisplay = new StringBuilder("Items:\n");
        for (Item item : availableItems.values()) {
            storeItemDisplay.append("  ").append(item.getDisplayInfo()).append("\n");
        }
        return storeItemDisplay.toString();
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

    public static Store constructDefaultStore() {
        return new Store(defaultItems());
    }

    private static Item[] defaultItems() {
        Item[] defaultItems = new Item[3];
        defaultItems[0] = new Item("item1", 12, 3);
        defaultItems[1] = new Item("item2", 6, 7);
        defaultItems[2] = new Item("item3", 7.5, 12.99);
        return defaultItems;
    }

    private void addAvailableItem(Item item) {
        availableItems.put(item.getName(), item);
    }
}
