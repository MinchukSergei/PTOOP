package by.bsuir.fksis.info.ptoop.products.meat;

import by.bsuir.fksis.info.ptoop.products.ProductItem;

@ProductItem
public class Sausage extends MeatProduct {
    public static float TAX_FACTOR = 0.2f;

    public Sausage() {
        super();
    }

    public Sausage(int weight, String name, int cost) {
        super(weight, name, cost);
    }

    @Override
    public float getTax() {
        return cost * TAX_FACTOR;
    }
}
