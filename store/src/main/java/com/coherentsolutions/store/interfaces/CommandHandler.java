package com.coherentsolutions.store.interfaces;

import com.coherentsolutions.store.Store;
import com.coherentsolutions.store.StorePrinter;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class CommandHandler {
    UserCommands commands;
    StorePrinter storePrinter;

    public CommandHandler(Store store) {
        commands = new UserCommands(store);
        storePrinter = new StorePrinter(store);
    }

    public void executeCommands(String command) throws IOException, ParserConfigurationException, SAXException {

        switch (command.toLowerCase()) {
            case "sort":
                System.out.println(commands.sort());
                break;
            case "top5":
                commands.top5();
                break;
            case "print":
                System.out.println(commands.print());
                break;
            case "create order":
                commands.order();
                commands.clearCart();
                break;
            case "quit": //quit does not work when clearCart command applies
                commands.quit();
                return; //How does it work?
            default:
                System.out.println("Unknown command: " + command);
        }
    }
}
