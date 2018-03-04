package by.bsuir.fksis.info.ptoop.plugin;

import by.bsuir.fksis.info.ptoop.console.command.AbstractCommand;

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
    List<AbstractCommand> getCommands();

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
