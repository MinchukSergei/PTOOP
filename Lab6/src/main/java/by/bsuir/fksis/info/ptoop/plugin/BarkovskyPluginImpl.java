package by.bsuir.fksis.info.ptoop.plugin;

import by.bsuir.fksis.info.ptoop.console.command.AbstractCommand;
import by.bsuir.fksis.info.ptoop.console.command.BarkovskyCommand;
import me.swarmer.ptoop.zipplugin.plugins.ZipPlugin;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Zip wrapper plugin impl
 */
public class BarkovskyPluginImpl implements ProductPlugin {
    private ZipPlugin zipPlugin = new ZipPlugin();

    @Override
    public List<AbstractCommand> getCommands() {
        BarkovskyCommand barkovskyCommand = new BarkovskyCommand(null);
        barkovskyCommand.setZipPlugin(zipPlugin);
        return Arrays.asList(barkovskyCommand);
    }

    @Override
    public List<Class> getProducts() {
        return Collections.emptyList();
    }

    @Override
    public OutputStream serializationWrap(OutputStream outputStream) {
        try {
            return zipPlugin.wrapOutputStream(outputStream);
        } catch (IOException e) {
            throw new RuntimeException("Cannot wrap output stream. " + e.getMessage());
        }
    }

    @Override
    public InputStream deserializationWrap(InputStream inputStream) {
        try {
            return zipPlugin.wrapInputStream(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Cannot wrap output stream. " + e.getMessage());
        }
    }
}
