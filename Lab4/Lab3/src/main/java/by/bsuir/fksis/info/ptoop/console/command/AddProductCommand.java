package by.bsuir.fksis.info.ptoop.console.command;

import by.bsuir.fksis.info.ptoop.console.ProductMenu;
import by.bsuir.fksis.info.ptoop.products.Product;

import java.io.IOException;
import java.util.List;

@CommandItem
public class AddProductCommand extends AbstractCommand {
    private List<Class> productNameList;

    public AddProductCommand(ProductMenu productMenu) {
        super(productMenu);
        productNameList = productMenu.getProductNameList();
    }

    @Override
    public String getCommandName() {
        return "Add product";
    }

    @Override
    public void run() {
        runProductChooser();
    }

    /**
     * Allow user to choose type of product to add
     */
    private void runProductChooser() {
        if (productNameList.isEmpty()) {
            System.out.println();
            return;
        }
        while(true) {
            printAllProductItems();
            Integer productItemIndex = getProductItemIndex();
            if (isValidProductIndex(productItemIndex)) {
                Class productClass = productNameList.get(productItemIndex);
                try {
                    Product newProduct = (Product) productClass.newInstance();
                    List<Product> productList = productMenu.getProductList();
                    productList.add(newProduct);
                    break;
                } catch (InstantiationException e) {
                    System.err.println("Cannot instantiate object for " + productClass.getSimpleName());
                } catch (IllegalAccessException e) {
                    System.err.println(productClass.getSimpleName() + " has no access for creation");
                }
            } else {
                System.out.println("Please, input number: 0-" + (productNameList.size() - 1));
            }
        }
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
        return productIndex != null && productIndex.compareTo(productNameList.size()) < 0;
    }

    private void printAllProductItems() {
        for (int i = 0; i < productNameList.size(); i++) {
            System.out.println(String.format("%d: %s", i, productNameList.get(i).getSimpleName()));
        }
    }
}
