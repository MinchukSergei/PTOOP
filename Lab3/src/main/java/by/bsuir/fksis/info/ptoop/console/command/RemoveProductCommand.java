package by.bsuir.fksis.info.ptoop.console.command;

import by.bsuir.fksis.info.ptoop.console.ProductMenu;
import by.bsuir.fksis.info.ptoop.products.Product;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class RemoveProductCommand extends AbstractCommand {
    private BufferedReader bufferedReader;
    public RemoveProductCommand(ProductMenu productMenu) {
        super(productMenu);
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run() {
        removeProductByIndex();
    }

    /**
     * Removes product by index
     */
    private void removeProductByIndex() {
        List<Product> productList = productMenu.getProductList();
        if (productList.isEmpty()) {
            System.out.println("Product list is empty.");
            return;
        }
        while (true) {
            System.out.println("Please, input index of product: ");
            Integer productIndex = getProductIndex();
            if (isValidProductIndex(productIndex)) {
                productList.remove(productIndex.intValue());
                break;
            } else {
                System.out.println("Please, input number: 0-" + (productList.size() - 1));
            }
        }
    }

    private Integer getProductIndex() {
        Integer productIndex = null;
        try {
            String userInput = bufferedReader.readLine();
            productIndex = Integer.parseInt(userInput);
        } catch (IOException | NumberFormatException ignored) {}

        return productIndex;
    }

    private boolean isValidProductIndex(Integer productIndex) {
        List<Product> productList = productMenu.getProductList();
        return productIndex != null && productIndex.compareTo(productList.size()) < 0;
    }
}
