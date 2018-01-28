package by.bsuir.fksis.info.ptoop.shape;

import by.bsuir.fksis.info.ptoop.util.Point2D;
import by.bsuir.fksis.info.ptoop.util.Polygon2D;

import java.util.Arrays;

public class Triangle extends Polygon2D {
    public Triangle(Point2D center, double radius) {
        super(center, radius, 3);
    }

    public Triangle(Point2D a, Point2D b, Point2D c) {
        super(Arrays.asList(a, b, c));
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "points=" + getPoints() +
                "}";
    }
}
