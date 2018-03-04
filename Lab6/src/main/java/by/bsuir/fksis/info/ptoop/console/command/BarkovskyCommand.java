package by.bsuir.fksis.info.ptoop.console.command;

import by.bsuir.fksis.info.ptoop.console.ProductMenu;
import me.swarmer.ptoop.zipplugin.plugins.ZipPlugin;

/**
 * Toggle adding zip wrapping
 */
@CommandItem
public class BarkovskyCommand extends AbstractCommand {
    private ZipPlugin zipPlugin;

    public void setZipPlugin(ZipPlugin zipPlugin) {
        this.zipPlugin = zipPlugin;
    }

    public BarkovskyCommand(ProductMenu productMenu) {
        super(productMenu);
    }

    @Override
    public String getCommandName() {
        return "Toggle zip wrapper";
    }

    @Override
    public void run() {
        zipPlugin.toggleZipping();
    }
}
