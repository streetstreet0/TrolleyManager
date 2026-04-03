package com.trolley.gui;

import com.trolley.trolley_management.Store;
import com.trolley.trolley_management.Trolley;
import com.trolley.trolley_management.exceptions.ItemNotFoundException;
import com.trolley.trolley_management.exceptions.TrolleyManagementException;
import com.trolley.gui.exceptions.UnavailableCommandException;
import com.trolley.gui.exceptions.UnknownCommandException;

import java.io.IOException;
import java.util.ArrayList;

public class UserMessageProcessor implements Runnable {
    private UserInterface ui;
    private Store store;
    private Trolley trolley;
    private boolean running;

    public UserMessageProcessor(Store store, Trolley trolley) {
        this.store = store;
        this.trolley = trolley;
        ui = new TerminalUserInterface();
    }

    public UserMessageProcessor(Store store, Trolley trolley, UserInterface ui) {
        this.store = store;
        this.trolley = trolley;
        this.ui = ui;
    }

    @Override
    public void run() {
        running = true;
        try {
            sayWelcomeMessage();
            while (running) {
                askForCommand();
            }
        }
        catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
        finally {
            ui.close();
        }
    }

    private void askForCommand() throws IOException {
        ui.tellUser("Available Commands:");
        ArrayList<UserCommand> availableCommands = new ArrayList<UserCommand>();
        availableCommands.add(UserCommand.ADD_ITEM);
        if (trolley.hasItems()) {
            availableCommands.add(UserCommand.REMOVE_ITEM);
            availableCommands.add(UserCommand.CLEAR_TROLLEY);
            availableCommands.add(UserCommand.DISPLAY_TROLLEY);
            availableCommands.add(UserCommand.CHECKOUT);
        }
        availableCommands.add(UserCommand.QUIT);
        for (UserCommand availableCommand : availableCommands) {
            ui.tellUser(availableCommand.displayMessage());
        }
        processMessage(ui.askQuestion("What would you like to do?"), availableCommands);
    }

    private void sayWelcomeMessage() {
        ui.tellUser("Welcome to the Trolley Management Software");
        ui.tellUser("The Items at this store are:");
        ui.tellUser(store.displayStoreItems());
    }

    private void processMessage(String message, ArrayList<UserCommand> availableCommands) throws IOException {
        try {
            UserCommand userCommand = UserCommand.getUserCommand(message);
            if (!availableCommands.contains(userCommand)) {
                throw new UnavailableCommandException(message, availableCommandsString(availableCommands));
            }
            processCommand(userCommand);
        }
        catch (UnknownCommandException | UnavailableCommandException commandException) {
            System.out.println(commandException.getMessage());
        }
    }

    private void processCommand(UserCommand userCommand) throws UnknownCommandException, IOException {
        switch (userCommand) {
            case UserCommand.ADD_ITEM:
                addItem();
                break;
            case UserCommand.REMOVE_ITEM:
                removeItem();
                break;
            /*
            Let's not implement these yet...
            case UserCommand.SORT_TROLLEY:
                sortTrolley();
                break;
            case UserCommand.CANCEL_SORT:
                cancelSort();
                break;
            */
            case UserCommand.CLEAR_TROLLEY:
                clearTrolley();
                break;
            case UserCommand.DISPLAY_TROLLEY:
                displayTrolley();
                break;
            case UserCommand.CHECKOUT:
                checkout();
                break;
            case UserCommand.QUIT:
                quit();
                break;
            default:
                throw new UnknownCommandException(userCommand.name());
        }
    }

    private void addItem() throws IOException {
        ui.tellUser("The Items at this store are:");
        ui.tellUser(store.displayStoreItems());
        String itemName;
        do {
            itemName = ui.askQuestion("Please select an item to add to the trolley:");
        }
        while (!store.hasItem(itemName));


        int quantity;
        while (true) {
            String quantityString = ui.askQuestion("How many do you wish to add:");
            try {
                quantity = Integer.parseInt(quantityString);
                if (quantity >= 0) {
                    break;
                }
                else {
                        ui.tellUser("Cannot add a negative number of " + itemName);
                        ui.tellUser("If you wish to remove items please select the " + UserCommand.REMOVE_ITEM.userInput() + "command");
                }
            } catch (NumberFormatException numberFormatException) {
                ui.tellUser("Please select a whole number quantity");
            }
        }

        if (quantity == 0) {
            ui.tellUser("adding 0 items");
        }
        else {
            try {
                trolley.addItem(itemName, quantity);
                ui.tellUser("Successfully added " + quantity + "x " + itemName + " to the trolley");
            }
            catch (ItemNotFoundException itemNotFoundException) {
                ui.tellUser(itemNotFoundException.getMessage());
            }
        }
        displayTrolley();
    }

    private void removeItem() throws IOException {
        ui.tellUser("The Items in the trolley:");
        ui.tellUser(trolley.displayItemsWithQuantity());
        String itemName = ui.askQuestion("Please select an item to remove from the trolley:");
        try {
            trolley.checkItemInTrolley(itemName);
            int quantity = -1;
            while (quantity < 0) {
                String quantityString = ui.askQuestion("How many do you wish to remove:");
                try {
                    quantity = Integer.parseInt(quantityString);
                    if (quantity < 0) {
                        ui.tellUser("Please select a positive number");
                    }
                } catch (NumberFormatException numberFormatException) {
                    ui.tellUser("Please select a whole number");
                }
            }

            if (quantity == 0) {
                ui.tellUser("removing 0 items");
            } else {
                trolley.removeItem(itemName, quantity);
                ui.tellUser("Successfully removed " + quantity + "x " + itemName + " from the trolley");
            }
        }
        catch (TrolleyManagementException trolleyManagementException) {
            ui.tellUser(trolleyManagementException.getMessage());
        }
        displayTrolley();
    }

    private void displayTrolley() {
        ui.tellUser(trolley.displayTrolley());
    }

    /*
    private void sortTrolley() {
    }

    private void cancelSort() {
    }
     */

    private void clearTrolley() {
        ui.tellUser("emptying trolley...");
        trolley.clearTrolley();
        ui.tellUser(trolley.displayTrolley());
        ui.tellUser("trolley emptied successfully");
        displayTrolley();
    }

    private void checkout() {
        ui.tellUser("Your final trolley was:");
        displayTrolley();
        ui.tellUser("Checking out...");
        running = false;
    }

    private void quit() {
        ui.tellUser("Closing trolley without checking out...");
        running = false;
    }

    private String availableCommandsString(ArrayList<UserCommand> availableCommands) {
        StringBuilder availableCommandsString = new StringBuilder();
        for (UserCommand availableUserCommand : availableCommands) {
            availableCommandsString.append(availableUserCommand.userInput()).append("\n");
        }
        return availableCommandsString.toString();
    }
}
