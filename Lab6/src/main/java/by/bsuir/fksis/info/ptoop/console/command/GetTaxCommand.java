package by.bsuir.fksis.info.ptoop.console.command;

import by.bsuir.fksis.info.ptoop.console.ProductMenu;
import by.bsuir.fksis.info.ptoop.products.Product;


import java.io.IOException;
import java.util.List;

/**
 * Gets tax from product
 */
@CommandItem
public class GetTaxCommand extends AbstractCommand {
    public GetTaxCommand(ProductMenu productMenu) {
        super(productMenu);
    }

    @Override
    public String getCommandName() {
        return "Get tax";
    }

    @Override
    public void run() {
        getTaxFromProduct();
    }

    /**
     * Gets tax from product
     */
    private void getTaxFromProduct() {
        List<Product> productList = productMenu.getProductList();
        if (productList.isEmpty()) {
            System.out.println("Product list is empty.");
            return;
        }
        while (true) {
            System.out.println("Please, input index of product: ");
            Integer productIndex = getProductItemIndex();
            if (isValidProductIndex(productIndex)) {
                Product product = productList.get(productIndex);
                System.out.println(product.getTax());
                break;
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
        List<Product> productList = productMenu.getProductList();
        return productIndex != null && productIndex.compareTo(productList.size()) < 0;
    }
}
