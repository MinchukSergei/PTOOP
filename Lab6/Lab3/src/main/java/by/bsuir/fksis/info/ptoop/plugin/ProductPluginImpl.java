package by.bsuir.fksis.info.ptoop.plugin;

import by.bsuir.fksis.info.ptoop.console.ProductMenu;
import by.bsuir.fksis.info.ptoop.console.command.AbstractCommand;
import by.bsuir.fksis.info.ptoop.console.command.CommandItem;
import by.bsuir.fksis.info.ptoop.exception.PackageNotFoundException;
import by.bsuir.fksis.info.ptoop.products.ProductItem;
import by.bsuir.fksis.info.ptoop.util.ClassSearcher;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Product plugin implementation
 */
public class ProductPluginImpl implements ProductPlugin {
    private final String productPackage = "by.bsuir.fksis.info.ptoop.products";
    private final String commandPackage = "by.bsuir.fksis.info.ptoop.console.command";
    private ProductPluginManager productPluginManager;

    public void setProductPluginManager(ProductPluginManager productPluginManager) {
        this.productPluginManager = productPluginManager;
    }

    @Override
    public List<AbstractCommand> getCommands() {
        try {
            List<AbstractCommand> commandList = new ArrayList<>();
            List<Class> commandClassList = ClassSearcher.getClasses(commandPackage, CommandItem.class);
            for (Class commandClass : commandClassList) {
                AbstractCommand command = (AbstractCommand) commandClass.getDeclaredConstructor(ProductMenu.class).newInstance(productPluginManager.getProductMenu());
                commandList.add(command);
            }
            return commandList;
        } catch (ClassNotFoundException | IOException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
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
