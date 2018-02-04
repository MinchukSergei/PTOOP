package by.bsuir.fksis.info.ptoop.shape;

import by.bsuir.fksis.info.ptoop.util.Point2D;
import by.bsuir.fksis.info.ptoop.util.Polygon2D;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Triangle extends Polygon2D {
    public static List<String> propertyNames = new ArrayList<>();
    static {
        propertyNames.add("Radius");
        propertyNames.add("Center x");
        propertyNames.add("Center y");
    }

    public Triangle(double radius, Point2D center) {
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

    /**
     * Construct new Triangle instance which is built from properties
     * @param propertiesValues properties for triangle
     * @return new Triangle instance
     */
    public static Triangle constructShape(Map<String, Integer> propertiesValues) {
        return new Triangle(propertiesValues.get("Radius"), new Point2D(propertiesValues.get("Center x"), propertiesValues.get("Center y")));
    }
}
