package by.bsuir.fksis.info.ptoop.console.command;

import by.bsuir.fksis.info.ptoop.console.ProductMenu;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Base class for command
 */
public abstract class AbstractCommand implements Runnable {
    protected ProductMenu productMenu;
    protected BufferedReader bufferedReader;

    protected AbstractCommand(ProductMenu productMenu) {
        this.productMenu = productMenu;
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    public abstract String getCommandName();
}
