package by.bsuir.fksis.info.ptoop.console.command;

import by.bsuir.fksis.info.ptoop.console.ProductMenu;
import by.bsuir.fksis.info.ptoop.products.Product;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import de.undercouch.bson4jackson.BsonFactory;
import de.undercouch.bson4jackson.BsonGenerator;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductDeserializationCommand extends AbstractCommand {
    public ProductDeserializationCommand(ProductMenu productMenu) {
        super(productMenu);
    }

    @Override
    public void run() {
        runDeserializeProductList();
    }

    /**
     * Starts deserialize product list
     */
    private void runDeserializeProductList() {
        String deserializeFileName = "src/main/resources/productSerialize";
        try {
            FileInputStream fis = new FileInputStream(new File(deserializeFileName));
            BufferedInputStream bis = new BufferedInputStream(fis);
            BsonFactory bf = new BsonFactory();
            bf.enable(BsonGenerator.Feature.ENABLE_STREAMING);
            JsonParser jp = bf.createJsonParser(bis);
            deserializeProducts(jp);
            jp.close();
        } catch (IOException e) {
            System.out.println(deserializeFileName + " was not found.");
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            System.out.println("File " + deserializeFileName + " has invalid format.");
        }
    }

    /**
     * Deserialize list of products
     * @param jp JsonParses object
     * @throws IOException Invalid bson file format
     * @throws ClassNotFoundException Invalid bson file format
     * @throws IllegalAccessException Invalid bson file format
     * @throws InstantiationException Invalid bson file format
     */
    private void deserializeProducts(JsonParser jp) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        List<Product> productList = new ArrayList<>();
        jp.nextToken();
        String productsFieldName = jp.nextFieldName();
        if ("products".equals(productsFieldName)) {
            while (jp.nextToken() != JsonToken.END_ARRAY) {
                if (jp.getCurrentToken() == JsonToken.START_OBJECT) {
                    Product newProduct = parseProduct(jp);
                    productList.add(newProduct);
                }
            }
            productMenu.setProductList(productList);
        } else {
            throw new IOException("Invalid file format.");
        }
    }

    /**
     * Parse product from BSON
     * @param jp JsonParses object
     * @return new Product object
     * @throws IOException Invalid bson file format
     * @throws ClassNotFoundException Invalid bson file format
     * @throws IllegalAccessException Invalid bson file format
     * @throws InstantiationException Invalid bson file format
     */
    private Product parseProduct(JsonParser jp) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        String productTypeFieldName = jp.nextFieldName();
        if ("productType".equals(productTypeFieldName)) {
            String productType = jp.nextTextValue();
            Product newProduct = (Product) Class.forName(productType).newInstance();
            jp.nextFieldName();
            int cost = jp.nextIntValue(0);
            jp.nextFieldName();
            String name = jp.nextTextValue();
            jp.nextFieldName();
            int weight = jp.nextIntValue(0);
            newProduct.setCost(cost);
            newProduct.setName(name);
            newProduct.setWeight(weight);
            return newProduct;
        } else {
            throw new IOException("Invalid file format.");
        }
    }
}
