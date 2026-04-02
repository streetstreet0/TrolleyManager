package com.trolley.exceptions;

import com.trolley.gui.UserCommand;

import java.lang.reflect.Array;

public class UnavailableCommandException extends RuntimeException {
  public UnavailableCommandException(String userInput) {
    super("Command: " + userInput + " is not an available command");
  }
}
