package com.coherentsolutions.store.interfaces;

import com.coherentsolutions.store.Store;
import com.coherentsolutions.store.print.StorePrinter;

public class CommandHandler {
    UserCommands commands;
    StorePrinter storePrinter;

    public CommandHandler(Store store) {
        commands = new UserCommands(store);
        storePrinter = new StorePrinter(store);
    }

    public void executeCommands(String command) {

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
            case "help":
                commands.help();
                break;
            default:
                System.out.println("Unknown command: " + command);
                commands.help();
                break;
        }
    }
}
