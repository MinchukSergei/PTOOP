package by.bsuir.fksis.info.ptoop.console;

import by.bsuir.fksis.info.ptoop.console.command.AbstractCommand;
import by.bsuir.fksis.info.ptoop.plugin.ProductPlugin;
import by.bsuir.fksis.info.ptoop.products.Product;
import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Product menu
 */
public class ProductMenu {
    private List<Pair<String, Runnable>> menuCommands;
    private List<Product> productList;
    private BufferedReader bufferedReader;
    private ProductPlugin productPlugin;

    /**
     * Returns product list
     * @return product list
     */
    public List<Product> getProductList() {
        return productList;
    }

    public List<Class> getProductNameList() {
        return productPlugin.getProducts();
    }

    public ProductPlugin getProductPlugin() {
        return productPlugin;
    }

    /**
     * Sets product list
     * @param productList product list
     */
    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public
    ProductMenu(ProductPlugin productPlugin) {
        productList = new ArrayList<>();
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        this.productPlugin = productPlugin;
        menuCommands = new ArrayList<>();
        setMenuCommands();
    }

    /**
     * Set available commands into menu
     */
    private void setMenuCommands() {
        List<Class> commands = productPlugin.getCommands();
        int index = 0;
        for (Class productCommand : commands) {
            try {
                AbstractCommand command = (AbstractCommand) productCommand.getDeclaredConstructor(ProductMenu.class).newInstance(this);
                menuCommands.add(new Pair<>(index++ + ": " + command.getCommandName(), command));
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                System.err.println(productCommand.getSimpleName() + " cannot be created.");
            }
        }
    }

    /**
     * Runs menu
     */
    public void runMenu() {
        while (true) {
            runMenuCommand();
        }
    }

    /**
     * Runs menu command
     */
    private void runMenuCommand() {
        do {
            System.out.println("Choose option:");
            menuCommands.forEach(p -> System.out.println(p.getKey()));

            Integer commandIndex = getCommandIndex();
            if (isValidCommandIndex(commandIndex)) {
                Runnable action = menuCommands.get(commandIndex).getValue();
                action.run();
                break;
            } else {
                System.out.println("Please, input number: 0-" + (menuCommands.size() - 1));
            }
        } while (true);
    }

    private Integer getCommandIndex() {
        Integer commandIndex = null;
        try {
            String userInput = bufferedReader.readLine();
            commandIndex = Integer.parseInt(userInput);
        } catch (IOException | NumberFormatException ignored) {}

        return commandIndex;
    }

    private boolean isValidCommandIndex(Integer commandIndex) {
        return commandIndex != null && commandIndex.compareTo(menuCommands.size()) < 0;
    }
}
