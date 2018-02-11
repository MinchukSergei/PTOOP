package by.bsuir.fksis.info.ptoop.console.command;

import by.bsuir.fksis.info.ptoop.console.ProductMenu;
import by.bsuir.fksis.info.ptoop.products.Product;

import java.io.IOException;
import java.util.List;

public class EditProductCommand extends AbstractCommand {
    public EditProductCommand(ProductMenu productMenu) {
        super(productMenu);
    }

    @Override
    public void run() {
        runEditingProduct();
    }

    /**
     * Starts edit command
     */
    private void runEditingProduct() {
        List<Product> productList = productMenu.getProductList();
        if (productList.isEmpty()) {
            System.out.println("Product list is empty.");
            return;
        }
        while (true) {
            System.out.println("Please, input index of product: ");
            Integer productIndex = getProductItemIndex();
            if (isValidProductIndex(productIndex)) {
                try {
                    editProduct(productIndex);
                    break;
                } catch (IOException | NumberFormatException e) {
                    System.out.println("Please input correct values: Cost - Integer, Name - String, Weight - Integer.");
                }
            }
        }
    }

    /**
     * Edits parameters for product
     * @param productIndex index of product
     * @throws IOException invalid parameter value
     * @throws NumberFormatException invalid parameter value
     */
    private void editProduct(int productIndex) throws IOException, NumberFormatException {
        System.out.println("Input cost:");
        int cost = getIntegerFromUser();
        System.out.println("Input name:");
        String name = getStringFromUser();
        System.out.println("Input weight:");
        int weight = getIntegerFromUser();
        List<Product> productList = productMenu.getProductList();
        Product product = productList.get(productIndex);
        product.setCost(cost);
        product.setName(name);
        product.setWeight(weight);
    }

    private int getIntegerFromUser() throws IOException, NumberFormatException {
        String userInput = getStringFromUser();
        return Integer.parseInt(userInput);
    }

    private String getStringFromUser() throws IOException {
        return bufferedReader.readLine();
    }

    private Integer getProductItemIndex() {
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
