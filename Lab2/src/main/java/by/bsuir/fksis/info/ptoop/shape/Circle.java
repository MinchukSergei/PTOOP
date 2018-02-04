package by.bsuir.fksis.info.ptoop.shape;

import by.bsuir.fksis.info.ptoop.drawing.shape.CircleDrawer;
import by.bsuir.fksis.info.ptoop.drawing.shape.ShapeDrawer;
import by.bsuir.fksis.info.ptoop.util.Point2D;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Circle implements Shape {
    private double r;
    private Point2D center;
    private ShapeDrawer shapeDrawer;
    public static List<String> propertyNames = new ArrayList<>();
    static {
        propertyNames.add("Radius");
        propertyNames.add("Center x");
        propertyNames.add("Center y");
    }

    public Circle() {
        this(1, new Point2D());
    }

    public Circle(double r, Point2D center) {
        if (r < 0) {
            throw new IllegalArgumentException("Radius should be positive.");
        }
        this.r = r;
        this.center = center;
        shapeDrawer = new CircleDrawer(this);
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
    public ShapeDrawer getShapeDrawer() {
        return shapeDrawer;
    }

    /**
     * Construct new Circle instance which is built from properties
     * @param propertiesValues properties for circle
     * @return new Circle instance
     */
    public static Circle constructShape(Map<String, Integer> propertiesValues) {
        return new Circle(propertiesValues.get("Radius"), new Point2D(propertiesValues.get("Center x"), propertiesValues.get("Center y")));
    }
}
