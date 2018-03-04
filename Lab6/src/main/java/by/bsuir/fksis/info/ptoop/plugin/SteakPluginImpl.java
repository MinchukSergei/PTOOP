package by.bsuir.fksis.info.ptoop.plugin;

import by.bsuir.fksis.info.ptoop.console.command.AbstractCommand;
import by.bsuir.fksis.info.ptoop.console.command.GetTaxCommand;
import by.bsuir.fksis.info.ptoop.products.meat.Steak;

import java.util.Arrays;
import java.util.List;

/**
 * Steak plugin impl
 */
public class SteakPluginImpl implements ProductPlugin {

    @Override
    public List<AbstractCommand> getCommands() {
        return Arrays.asList(new GetTaxCommand(null));
    }

    @Override
    public List<Class> getProducts() {
        return Arrays.asList(Steak.class);
    }
}
