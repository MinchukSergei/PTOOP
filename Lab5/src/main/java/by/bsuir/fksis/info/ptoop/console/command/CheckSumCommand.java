package by.bsuir.fksis.info.ptoop.console.command;

import by.bsuir.fksis.info.ptoop.console.ProductMenu;
import by.bsuir.fksis.info.ptoop.plugin.ChecksumPluginImpl;

import java.io.IOException;

/**
 * Toggle adding checksum to serialization file
 */
@CommandItem
public class CheckSumCommand extends AbstractCommand {

    public CheckSumCommand(ProductMenu productMenu) {
        super(productMenu);
    }

    @Override
    public String getCommandName() {
        return "Checksum";
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("Please, input 1 (turn on) or 0 (turn off): ");
            Integer productIndex = getProductItemIndex();
            if (isValidProductIndex(productIndex)) {
                ChecksumPluginImpl.VALIDATION = productIndex.equals(1);
                break;
            }
        }
    }

    private Integer getProductItemIndex() {
        Integer productIndex = null;
        try {
            String userInput = bufferedReader.readLine();
            productIndex = Integer.parseInt(userInput);
        } catch (IOException | NumberFormatException ignored) {}

        return productIndex;
    }

    private boolean isValidProductIndex(Integer productIndex) {
        return productIndex >= 0 && productIndex <= 1;
    }
}
