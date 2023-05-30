package com.coherentsolutions.store;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Scanner;

public class StoreCommandHandler {
    Store store;

    public StoreCommandHandler(Store store) {
        this.store = store;
    }

    public void readCommands() throws IOException, ParserConfigurationException, SAXException {
        Scanner scanner = new Scanner(System.in);
        String command;

        while (true) {
            UserCommands commands = new UserCommands(store);
            System.out.println("Enter a command: ");
            command = scanner.nextLine();

            switch (command.toLowerCase()) {
                case "sort":
                    commands.sort();
                    break;
                case "top5":
                    commands.top5();
                    break;
                case "print":
                    commands.print();
                    break;
                case "quit":
                    commands.quit();
                    return; //How does it work?
                default:
                    System.out.println("Unknown command: " + command);
            }
        }
    }
}
