package com.trolley.gui;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;

class TerminalUserInterface implements UserInterface {
    private final Scanner scanner;

    TerminalUserInterface() {
        scanner = new Scanner(System.in);
    }

    public void close() {
        scanner.close();
    }

    private void println(String string) {
        System.out.println(string);
    }

    @Override
    public String askQuestion(String question) throws IOException {
        println(question);
        return scanner.nextLine().trim();
    }

    @Override
    public void tellUser(String message) {
        println(message);
    }

}
