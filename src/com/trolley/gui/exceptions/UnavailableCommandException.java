package com.trolley.gui.exceptions;

public class UnavailableCommandException extends UserCommandException {
  public UnavailableCommandException(String userInput, String availableCommands) {

    super("Command: " + userInput + " is not an available command\nThe following commands are:\n" + availableCommands);
  }
}
