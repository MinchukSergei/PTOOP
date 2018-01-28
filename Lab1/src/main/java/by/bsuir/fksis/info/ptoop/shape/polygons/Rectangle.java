package by.bsuir.fksis.info.ptoop.shape.polygons;

import by.bsuir.fksis.info.ptoop.util.Point2D;

import java.util.Arrays;

public class Rectangle extends Polygon2D {

    public Rectangle(Point2D a, Point2D c) {
        super();
        if (c.getY() < a.getY()) {
            throw new IllegalArgumentException("c.x should be more than a.x.");
        }
        if (c.getX() < a.getX()) {
            throw new IllegalArgumentException("c.y should be more than a.y.");
        }
        fillPoints(a, c);
    }

    public void fillPoints(Point2D a, Point2D c) {
        double height = c.getY() - a.getY();
        Point2D b = new Point2D();
        Point2D d = new Point2D();
        b.setX(a.getX());
        b.setY(a.getY() + height);
        d.setX(c.getX());
        d.setY(c.getY() - height);
        setPoints(Arrays.asList(a, b, c, d));
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "points=" + getPoints() +
                "}";
    }
}
