package by.bsuir.fksis.info.ptoop.products.meat;

import by.bsuir.fksis.info.ptoop.products.AbstractProduct;

/**
 * Base meat class
 */
public abstract class MeatProduct extends AbstractProduct {

    public MeatProduct() {
        super();
    }

    public MeatProduct(int weight, String name, int cost) {
        super(weight, name, cost);
    }

    public boolean isSuitableForVegans() {
        return false;
    }
}
