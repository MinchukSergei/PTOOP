package by.bsuir.fksis.info.ptoop.drawing;

import by.bsuir.fksis.info.ptoop.shape.Shape;

import javax.swing.*;
import java.awt.*;

public class ShapeFrame extends JFrame {
    private Shape shape;

    public ShapeFrame(Shape shape) throws HeadlessException {
        this.shape = shape;
        setConfig();
    }

    private void setConfig() {
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D gg = (Graphics2D) g;
        gg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        shape.draw(gg);
    }
}
