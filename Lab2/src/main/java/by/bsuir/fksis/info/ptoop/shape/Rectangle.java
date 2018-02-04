package by.bsuir.fksis.info.ptoop.shape;

import by.bsuir.fksis.info.ptoop.util.Point2D;
import by.bsuir.fksis.info.ptoop.util.Polygon2D;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Rectangle extends Polygon2D {
    public static List<String> propertyNames = new ArrayList<>();
    static {
        propertyNames.add("Left top x");
        propertyNames.add("Left top y");
        propertyNames.add("Right bottom x");
        propertyNames.add("Right bottom y");
    }

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

    /**
     * Fill right top and left bottom points for the rectangle
     * @param a left top point
     * @param c right bottom point
     */
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

    /**
     * Construct new Rectangle instance which is built from properties
     * @param propertiesValues properties for rectangle
     * @return new Rectangle instance
     */
    public static Rectangle constructShape(Map<String, Integer> propertiesValues) {
        return new Rectangle(new Point2D(propertiesValues.get("Left top x"), propertiesValues.get("Left top y")),
                new Point2D(propertiesValues.get("Right bottom x"), propertiesValues.get("Right bottom y")));
    }
}
