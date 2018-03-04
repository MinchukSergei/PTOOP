package by.bsuir.fksis.info.ptoop.plugin;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * Product plugin
 */
public interface ProductPlugin {
    /**
     * Gets commands from plugin
     * @return commands from plugin
     */
    List<Class> getCommands();

    /**
     * Gets products from plugin
     * @return products from plugin
     */
    List<Class> getProducts();

    default OutputStream serializationWrap(OutputStream outputStream) {
        return outputStream;
    }

    default InputStream deserializationWrap(InputStream inputStream) {
        return inputStream;
    }
}
