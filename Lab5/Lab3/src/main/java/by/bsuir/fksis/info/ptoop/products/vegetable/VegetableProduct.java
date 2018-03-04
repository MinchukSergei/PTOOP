package by.bsuir.fksis.info.ptoop.products.vegetable;

import by.bsuir.fksis.info.ptoop.products.AbstractProduct;

/**
 * Base vegetable class
 */
public abstract class VegetableProduct extends AbstractProduct {

    public VegetableProduct() {
        super();
    }

    public VegetableProduct(int weight, String name, int cost) {
        super(weight, name, cost);
    }

    public boolean isSuitableForVegans() {
        return true;
    }
}
