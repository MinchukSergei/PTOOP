package by.bsuir.fksis.info.ptoop.drawing.shape;

import by.bsuir.fksis.info.ptoop.shape.Line2D;

import java.awt.*;

/**
 * Drawer for Line2D
 */
public class Line2DDrawer implements ShapeDrawer {
    private Line2D line2D;

    public Line2DDrawer(Line2D line2D) {
        this.line2D = line2D;
    }

    public Line2D getLine2D() {
        return line2D;
    }

    public void setLine2D(Line2D line2D) {
        this.line2D = line2D;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.draw(new java.awt.geom.Line2D.Double(line2D.getP1().getX(), line2D.getP1().getY(), line2D.getP2().getX(), line2D.getP2().getY()));
    }
}
