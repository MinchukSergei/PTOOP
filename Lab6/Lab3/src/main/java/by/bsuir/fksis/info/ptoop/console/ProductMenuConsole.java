package by.bsuir.fksis.info.ptoop.console;

import by.bsuir.fksis.info.ptoop.plugin.ProductPluginManager;

import java.util.Arrays;

/**
 * Product menu runner
 */
public class ProductMenuConsole {
    public static void main(String[] args) {
        ProductPluginManager productPluginManager = new ProductPluginManager(Arrays.asList(args));
        ProductMenu productMenu = new ProductMenu(productPluginManager);
        productMenu.runMenu();
    }
}
