package by.bsuir.fksis.info.ptoop.console.command;

import by.bsuir.fksis.info.ptoop.console.ProductMenu;

public class ExitCommand extends AbstractCommand {
    public ExitCommand(ProductMenu productMenu) {
        super(productMenu);
    }

    @Override
    public void run() {
        System.exit(0);
    }
}
