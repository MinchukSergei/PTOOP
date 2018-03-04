package by.bsuir.fksis.info.ptoop.plugin;

import by.bsuir.fksis.info.ptoop.console.command.AbstractCommand;
import by.bsuir.fksis.info.ptoop.console.command.CheckSumCommand;
import by.bsuir.fksis.info.ptoop.console.command.checksum.ChecksumInputStream;
import by.bsuir.fksis.info.ptoop.console.command.checksum.ChecksumOutputStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Checksum plugin impl
 */
public class ChecksumPluginImpl implements ProductPlugin {
    public static boolean VALIDATION = true;

    @Override
    public List<AbstractCommand> getCommands() {
        return Arrays.asList(new CheckSumCommand(null));
    }

    @Override
    public List<Class> getProducts() {
        return Collections.emptyList();
    }

    @Override
    public OutputStream serializationWrap(OutputStream outputStream) {
        if (!VALIDATION) {
            return outputStream;
        }
        return new ChecksumOutputStream(outputStream);
    }

    @Override
    public InputStream deserializationWrap(InputStream inputStream) {
        if (!VALIDATION) {
            return inputStream;
        }
        try {
            return new ChecksumInputStream(inputStream.readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException("Error while deserialization. " + e.getMessage());
        }
    }
}
