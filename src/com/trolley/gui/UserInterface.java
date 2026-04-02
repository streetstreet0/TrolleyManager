package com.trolley.gui;

import java.io.IOException;

public interface UserInterface {
    public String askQuestion(String question) throws IOException;
    public void tellUser(String message);
    public void close();
}
