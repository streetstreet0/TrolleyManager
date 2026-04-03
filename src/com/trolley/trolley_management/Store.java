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
        Item[] defaultItems = new Item[10];
        defaultItems[0] = new Item("beef mince", 300, 5.40);
        defaultItems[1] = new Item("beef rump steak", 300, 7.80);
        defaultItems[2] = new Item("beef sausages", 300, 8.49);
        defaultItems[3] = new Item("skinless chicken breast", 500, 6.00);
        defaultItems[4] = new Item("boneless skinless chicken thighs", 400, 7.99);
        defaultItems[5] = new Item("pork mince", 500, 11.49);
        defaultItems[6] = new Item("pork leg roast", 1500, 13.49);
        defaultItems[7] = new Item("venison burger patties", 400, 10.99);
        defaultItems[8] = new Item("fresh snapper fillet", 100, 4.90);
        defaultItems[9] = new Item("prawn cutlets", 800, 11.99);
        return defaultItems;
    }

    private void addAvailableItem(Item item) {
        availableItems.put(item.getName(), item);
    }
}
