package com.trolley.gui;

import com.trolley.Store;
import com.trolley.Trolley;
import com.trolley.exceptions.UnavailableCommandException;
import com.trolley.exceptions.UnknownCommandException;

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
            if (!availableCommands.contains(userCommand))
                throw new UnavailableCommandException(message);
            processCommand(userCommand);
        }
        catch (UnknownCommandException | UnavailableCommandException commandException) {
            System.out.println(commandException.getMessage());
        }
    }

    private void processCommand(UserCommand userCommand) throws UnavailableCommandException, UnknownCommandException, IOException {
        switch (userCommand) {
            case UserCommand.ADD_ITEM:
                addItem();
            case UserCommand.REMOVE_ITEM:
                removeItem();
            case UserCommand.SORT_TROLLEY:
                sortTrolley();
            case UserCommand.CANCEL_SORT:
                cancelSort();
            case UserCommand.CLEAR_TROLLEY:
                clearTrolley();
            case UserCommand.DISPLAY_TROLLEY:
                displayTrolley();
            case UserCommand.CHECKOUT:
                checkout();
            default:
                throw new UnknownCommandException(userCommand.name());
        }
    }

    private void addItem() {

    }

    private void removeItem() {

    }

    private void displayTrolley() {
    }

    private void sortTrolley() {
    }

    private void cancelSort() {
    }

    private void clearTrolley() {
    }

    private void checkout() {
    }
}
