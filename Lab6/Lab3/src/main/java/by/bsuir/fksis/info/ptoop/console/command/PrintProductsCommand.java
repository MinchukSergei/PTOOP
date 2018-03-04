package by.bsuir.fksis.info.ptoop.console.command;

import by.bsuir.fksis.info.ptoop.console.ProductMenu;
import by.bsuir.fksis.info.ptoop.products.Product;

import java.util.List;

@CommandItem
public class PrintProductsCommand extends AbstractCommand {

    public PrintProductsCommand(ProductMenu productMenu) {
        super(productMenu);
    }

    @Override
    public String getCommandName() {
        return "Print all products";
    }

    @Override
    public void run() {
        List<Product> productList = productMenu.getProductList();
        productList.forEach(System.out::println);
    }
}
