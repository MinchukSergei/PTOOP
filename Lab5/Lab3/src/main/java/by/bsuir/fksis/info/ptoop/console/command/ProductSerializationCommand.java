package by.bsuir.fksis.info.ptoop.console.command;

import by.bsuir.fksis.info.ptoop.console.ProductMenu;
import by.bsuir.fksis.info.ptoop.plugin.ProductPlugin;
import by.bsuir.fksis.info.ptoop.plugin.ProductPluginManager;
import by.bsuir.fksis.info.ptoop.products.Product;
import com.fasterxml.jackson.core.JsonGenerator;
import de.undercouch.bson4jackson.BsonFactory;
import de.undercouch.bson4jackson.BsonGenerator;

import java.io.*;
import java.util.List;

@CommandItem
public class ProductSerializationCommand extends AbstractCommand {
    public ProductSerializationCommand(ProductMenu productMenu) {
        super(productMenu);
    }

    @Override
    public String getCommandName() {
        return "Serialize products";
    }

    @Override
    public void run() {
        runSerializeProductList();
    }

    /**
     * Starts serialize products
     */
    private void runSerializeProductList() {
        String serializeFilePath = "src/main/java/productSerialize";
        try {
            File serializeFile = new File(serializeFilePath);
            OutputStream os = new FileOutputStream(serializeFile);
            os = wrapStream(os);
            BufferedOutputStream bos = new BufferedOutputStream(os);
            BsonFactory bf = new BsonFactory();
            bf.enable(BsonGenerator.Feature.ENABLE_STREAMING);
            JsonGenerator jg = bf.createGenerator(bos);
            serializeProducts(jg);
            jg.close();
        } catch (IOException e) {
            System.err.println(serializeFilePath + " was not found.");
        }
    }

    private OutputStream wrapStream(OutputStream os) {
        ProductPluginManager productPluginManager = productMenu.getProductPluginManager();
        List<ProductPlugin> productPlugins = productPluginManager.getProductPlugins();
        for (ProductPlugin productPlugin : productPlugins) {
            os = productPlugin.serializationWrap(os);
        }
        return os;
    }

    /**
     * Serializes products
     * @param jg JsonGenerator object
     * @throws IOException writing error
     */
    private void serializeProducts(JsonGenerator jg) throws IOException {
        List<Product> productList = productMenu.getProductList();
        jg.writeStartObject();
        jg.writeArrayFieldStart("products");
        for (Product p : productList) {
            jg.writeStartObject();
            jg.writeFieldName("productType");
            jg.writeString(p.getClass().getName());
            jg.writeFieldName("cost");
            jg.writeNumber(p.getCost());
            jg.writeFieldName("name");
            jg.writeString(p.getName());
            jg.writeFieldName("weight");
            jg.writeNumber(p.getWeight());
            jg.writeEndObject();
        }
        jg.writeEndArray();
        jg.writeEndObject();
    }
}
