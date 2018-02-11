package by.bsuir.fksis.info.ptoop.console;

import by.bsuir.fksis.info.ptoop.console.command.*;
import by.bsuir.fksis.info.ptoop.products.Product;
import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Product menu
 */
public class ProductMenu {
    private List<Pair<String, Runnable>> menuCommands;
    private List<Product> productList;
    private BufferedReader bufferedReader;

    /**
     * Returns product list
     * @return product list
     */
    public List<Product> getProductList() {
        return productList;
    }

    /**
     * Sets product list
     * @param productList product list
     */
    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public ProductMenu() {
        menuCommands = new ArrayList<>();
        productList = new ArrayList<>();
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        setMenuCommands();
    }

    /**
     * Set available commands into menu
     */
    private void setMenuCommands() {
        menuCommands.add(new Pair<>("0. Exit", new ExitCommand(this)));
        menuCommands.add(new Pair<>("1. Print all products", new PrintProductsCommand(this)));
        menuCommands.add(new Pair<>("2. Add product", new AddProductCommand(this)));
        menuCommands.add(new Pair<>("3. Remove product", new RemoveProductCommand(this)));
        menuCommands.add(new Pair<>("4. Edit product", new EditProductCommand(this)));
        menuCommands.add(new Pair<>("5. Serialize products", new ProductSerializationCommand(this)));
        menuCommands.add(new Pair<>("6. Deserialize products", new ProductDeserializationCommand(this)));
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
