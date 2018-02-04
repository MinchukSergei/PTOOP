package by.bsuir.fksis.info.ptoop.drawing;

import by.bsuir.fksis.info.ptoop.shape.Shape;

import javax.swing.*;
import java.awt.*;

/**
 * Panel for drawing shapes
 */
public class ShapeDrawPanel extends JPanel {
    private Shape shape;

    public ShapeDrawPanel(Shape shape) {
        this.shape = shape;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        shape.getShapeDrawer().draw(g2d);
    }
}
