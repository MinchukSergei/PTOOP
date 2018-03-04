package by.bsuir.fksis.info.ptoop.products;

/**
 * Base product class
 */
public abstract class AbstractProduct implements Product {
    protected int weight;
    protected String name;
    protected int cost;

    protected AbstractProduct(int weight, String name, int cost) {
        this.weight = weight;
        this.name = name;
        this.cost = cost;
    }

    protected AbstractProduct() {
        this(0, "Emtpy Product", 0);
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" +
                "weight=" + weight +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                '}';
    }
}
