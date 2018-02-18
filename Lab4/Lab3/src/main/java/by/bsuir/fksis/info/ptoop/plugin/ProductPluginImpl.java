package by.bsuir.fksis.info.ptoop.plugin;

import by.bsuir.fksis.info.ptoop.console.command.CommandItem;
import by.bsuir.fksis.info.ptoop.exception.PackageNotFoundException;
import by.bsuir.fksis.info.ptoop.products.ProductItem;
import by.bsuir.fksis.info.ptoop.util.ClassSearcher;

import java.io.IOException;
import java.util.List;

/**
 * Product plugin implementation
 */
public class ProductPluginImpl implements ProductPlugin {
    private final String productPackage = "by.bsuir.fksis.info.ptoop.products";
    private final String commandPackage = "by.bsuir.fksis.info.ptoop.console.command";

    @Override
    public List<Class> getCommands() {
        try {
            return ClassSearcher.getClasses(commandPackage, CommandItem.class);
        } catch (ClassNotFoundException | IOException e) {
            throw new PackageNotFoundException("Package " + commandPackage + " was not found", e);
        }
    }

    @Override
    public List<Class> getProducts() {
        try {
            return ClassSearcher.getClasses(productPackage, ProductItem.class);
        } catch (ClassNotFoundException | IOException e) {
            throw new PackageNotFoundException("Package " + productPackage + " was not found", e);
        }
    }
}
