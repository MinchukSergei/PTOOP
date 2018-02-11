package by.bsuir.fksis.info.ptoop.products;

/**
 * Product interface
 */
public interface Product {
    /**
     * Returns product weight
     * @return product weight
     */
    int getWeight();

    /**
     * Sets product weight
     * @param weight product weight
     */
    void setWeight(int weight);

    /**
     * Returns product name
     * @return product name
     */
    String getName();

    /**
     * Sets product name
     * @param name product name
     */
    void setName(String name);

    /**
     * Returns product cost
     * @return product cost
     */
    int getCost();

    /**
     * Sets product cost
     * @param cost product cost
     */
    void setCost(int cost);

    /**
     * Returns product tax
     * @return product tax
     */
    float getTax();

    /**
     * Returns product is suitable for vegans
     * @return product is suitable for vegans
     */
    boolean isSuitableForVegans();
}
