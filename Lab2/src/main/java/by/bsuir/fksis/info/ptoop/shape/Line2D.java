package by.bsuir.fksis.info.ptoop.shape;

import by.bsuir.fksis.info.ptoop.drawing.shape.Line2DDrawer;
import by.bsuir.fksis.info.ptoop.drawing.shape.ShapeDrawer;
import by.bsuir.fksis.info.ptoop.util.Point2D;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Line2D implements Shape {
    private Point2D p1;
    private Point2D p2;
    private ShapeDrawer shapeDrawer;

    public static List<String> propertyNames = new ArrayList<>();
    static {
        propertyNames.add("Point A x");
        propertyNames.add("Point A y");
        propertyNames.add("Point B x");
        propertyNames.add("Point B y");
    }

    public Line2D() {
        this(new Point2D(), new Point2D());
    }

    public Line2D(Point2D p1, Point2D p2) {
        this.p1 = p1;
        this.p2 = p2;
        shapeDrawer = new Line2DDrawer(this);
    }

    public Point2D getP1() {
        return p1;
    }

    public void setP1(Point2D p1) {
        this.p1 = p1;
    }

    public Point2D getP2() {
        return p2;
    }

    public void setP2(Point2D p2) {
        this.p2 = p2;
    }

    @Override
    public ShapeDrawer getShapeDrawer() {
        return shapeDrawer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Line2D line2D = (Line2D) o;
        return Objects.equals(p1, line2D.p1) &&
                Objects.equals(p2, line2D.p2);
    }

    @Override
    public int hashCode() {

        return Objects.hash(p1, p2);
    }

    @Override
    public String toString() {
        return "Line2D{" +
                "p1=" + p1 +
                ", p2=" + p2 +
                '}';
    }

    /**
     * Construct new Line2D instance which is built from properties
     * @param propertiesValues properties for line
     * @return new Line2D instance
     */
    public static Line2D constructShape(Map<String, Integer> propertiesValues) {
        return new Line2D(new Point2D(propertiesValues.get("Point A x"), propertiesValues.get("Point A y")),
                new Point2D(propertiesValues.get("Point B x"), propertiesValues.get("Point B y")));
    }
}
