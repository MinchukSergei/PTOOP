package by.bsuir.fksis.info.ptoop.products.vegetable;

import by.bsuir.fksis.info.ptoop.products.ProductItem;

@ProductItem
public class Tomato extends VegetableProduct {
    public static float TAX_FACTOR = 0.2f;

    public Tomato() {
        super();
    }

    public Tomato(int weight, String name, int cost) {
        super(weight, name, cost);
    }

    @Override
    public float getTax() {
        return cost * TAX_FACTOR / 1.15f + 5;
    }
}
