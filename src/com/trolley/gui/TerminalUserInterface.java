package com.trolley.gui;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;

class TerminalUserInterface implements UserInterface {
    private Scanner scanner;
    private OutputStreamWriter writer;
    private UserMessageProcessor messageProcessor;

    TerminalUserInterface() {
        this(System.in, System.out);
    }

    TerminalUserInterface(InputStream inputStream, OutputStream outputStream) {
        scanner = new Scanner(inputStream);
        writer = new OutputStreamWriter(outputStream);
        messageProcessor = new UserMessageProcessor(this);
    }

    @Override
    public String askForAddItemArg() {
        return "";
    }

    @Override
    public String askForRemoveItemArg() {
        return "";
    }
}
