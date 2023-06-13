package com.coherentsolutions.store.interfaces;

import com.coherentsolutions.store.Store;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class CommandHandler {
    public void executeCommands(String command, Store store) throws IOException, ParserConfigurationException, SAXException {
        UserCommands commands = new UserCommands(store);

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
