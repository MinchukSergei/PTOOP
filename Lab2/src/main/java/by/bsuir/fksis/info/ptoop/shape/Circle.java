package by.bsuir.fksis.info.ptoop.shape;

import by.bsuir.fksis.info.ptoop.util.Point2D;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Circle implements Shape {
    private double r;
    private Point2D center;

    public Circle() {
        this(1, new Point2D());
    }

    public Circle(double r, Point2D center) {
        if (r < 0) {
            throw new IllegalArgumentException("Radius should be positive.");
        }
        this.r = r;
        this.center = center;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public Point2D getCenter() {
        return center;
    }

    public void setCenter(Point2D center) {
        this.center = center;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.draw(new Ellipse2D.Double(center.getX(), center.getY(), r, r));
    }
}
