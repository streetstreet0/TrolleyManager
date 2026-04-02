package com.trolley.gui;

import com.trolley.exceptions.UnknownCommandException;

public class UserMessageProcessor {
    private UserInterface ui;

    public UserMessageProcessor() {
        ui = new TerminalUserInterface();
    }

    public UserMessageProcessor(UserInterface ui) {
        this.ui = ui;
    }

    public void askForCommand() {

    }

    public void processMessage(String message) {
        try {
            UserCommand userCommand = UserCommand.getUserCommand(message);
            processCommand(userCommand);
        }
        catch (UnknownCommandException unknownCommandException) {
            System.out.println(unknownCommandException.getMessage());
        }
    }

    private void processCommand(UserCommand userCommand) throws UnknownCommandException {
        switch (userCommand) {
            case UserCommand.ADD_ITEM:

                String addItemArg = ui.askForAddItemArg();
            case UserCommand.REMOVE_ITEM:
                String removeItemArg = ui.askForRemoveItemArg();
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

    private String askForAddItemRequestString() {
        return null;
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
