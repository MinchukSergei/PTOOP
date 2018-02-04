package by.bsuir.fksis.info.ptoop.drawing.shape;

import by.bsuir.fksis.info.ptoop.shape.Circle;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Drawer for circle
 */
public class CircleDrawer implements ShapeDrawer {
    private Circle circle;

    public CircleDrawer(Circle circle) {
        this.circle = circle;
    }

    public Circle getCircle() {
        return circle;
    }

    public void setCircle(Circle circle) {
        this.circle = circle;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.draw(new Ellipse2D.Double(circle.getCenter().getX(), circle.getCenter().getY(), circle.getR(), circle.getR()));
    }
}
