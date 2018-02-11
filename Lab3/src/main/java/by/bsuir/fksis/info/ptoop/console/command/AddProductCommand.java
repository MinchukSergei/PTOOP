package by.bsuir.fksis.info.ptoop.console.command;

import by.bsuir.fksis.info.ptoop.console.ProductMenu;
import by.bsuir.fksis.info.ptoop.products.Product;
import by.bsuir.fksis.info.ptoop.util.ClassSearcher;

import java.io.IOException;
import java.util.List;
import java.util.MissingResourceException;

public class AddProductCommand extends AbstractCommand {
    private List<Class> productItemList;

    public AddProductCommand(ProductMenu productMenu) {
        super(productMenu);
        productItemList = getProductItemClasses();
    }

    @Override
    public void run() {
        runProductChooser();
    }

    /**
     * Allow user to choose type of product to add
     */
    private void runProductChooser() {
        if (productItemList.isEmpty()) {
            System.out.println();
            return;
        }
        while(true) {
            printAllProductItems();
            Integer productItemIndex = getProductItemIndex();
            if (isValidProductIndex(productItemIndex)) {
                Class productClass = productItemList.get(productItemIndex);
                try {
                    Product newProduct = (Product) productClass.newInstance();
                    List<Product> productList = productMenu.getProductList();
                    productList.add(newProduct);
                    break;
                } catch (InstantiationException e) {
                    System.out.println("Cannot instantiate object for " + productClass.getSimpleName());
                } catch (IllegalAccessException e) {
                    System.out.println(productClass.getSimpleName() + " has no access for creation");
                }
            } else {
                System.out.println("Please, input number: 0-" + (productItemList.size() - 1));
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
        return productIndex != null && productIndex.compareTo(productItemList.size()) < 0;
    }

    private void printAllProductItems() {
        for (int i = 0; i < productItemList.size(); i++) {
            System.out.println(String.format("%d: %s", i, productItemList.get(i).getSimpleName()));
        }
    }

    /**
     * Gets all classes which are marked with ProductItem annotation
     * @return all classes which are marked with ProductItem annotation
     * @throws MissingResourceException if package was not found
     */
    private List<Class> getProductItemClasses() throws MissingResourceException {
        String packageName = "by.bsuir.fksis.info.ptoop.products";
        List<Class> productItemList;
        try {
            productItemList = ClassSearcher.getClasses(packageName);
        } catch (ClassNotFoundException | IOException e) {
            throw new IllegalArgumentException("Invalid package name: " + packageName);
        }
        return productItemList;
    }
}
