package com.coherentsolutions.store.interfaces;

import com.coherentsolutions.store.Store;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Scanner;

public class UserInterface {
    Store store;
    CommandHandler commandHandler;

    public UserInterface(Store store) {
        this.store = store;
        commandHandler = new CommandHandler();
    }

    public void readCommands() throws IOException, ParserConfigurationException, SAXException {
        Scanner scanner = new Scanner(System.in);
        String command = "";

        //I need to change it...
        while (!command.equalsIgnoreCase("quit")) {
            System.out.println("Enter a command: ");
            command = scanner.nextLine();
            commandHandler.executeCommands(command, store);
        }
    }
}
