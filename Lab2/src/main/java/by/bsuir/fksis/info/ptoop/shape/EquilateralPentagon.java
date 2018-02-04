package by.bsuir.fksis.info.ptoop.shape;

import by.bsuir.fksis.info.ptoop.util.Point2D;
import by.bsuir.fksis.info.ptoop.util.Polygon2D;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EquilateralPentagon extends Polygon2D {
    public static List<String> propertyNames = new ArrayList<>();
    static {
        propertyNames.add("Radius");
        propertyNames.add("Center x");
        propertyNames.add("Center y");
    }

    public EquilateralPentagon(double R, Point2D center) {
        super(center, R, 5);
    }

    @Override
    public String toString() {
        return "EquilateralPentagon{" +
                "points=" + getPoints() +
                "}";
    }

    /**
     * Construct new EquilateralPentagon instance which is built from properties
     * @param propertiesValues properties for pentagon
     * @return new EquilateralPentagon instance
     */
    public static EquilateralPentagon constructShape(Map<String, Integer> propertiesValues) {
        return new EquilateralPentagon(propertiesValues.get("Radius"), new Point2D(propertiesValues.get("Center x"), propertiesValues.get("Center y")));
    }
}
