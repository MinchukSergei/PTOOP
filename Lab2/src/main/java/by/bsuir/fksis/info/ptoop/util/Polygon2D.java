package by.bsuir.fksis.info.ptoop.util;

import by.bsuir.fksis.info.ptoop.drawing.shape.PolygonDrawer;
import by.bsuir.fksis.info.ptoop.drawing.shape.ShapeDrawer;
import by.bsuir.fksis.info.ptoop.shape.Shape;

import java.util.ArrayList;
import java.util.List;

/**
 * The basic class for Heptagon, Polygon, Triangle
 */
public class Polygon2D implements Shape {
    private List<Point2D> points;
    private ShapeDrawer shapeDrawer;

    public Polygon2D(Point2D center, double r, int n) {
        this();
        if (r < 0) {
            throw new IllegalArgumentException("Radius should be positive.");
        }
        if (n < 3) {
            throw new IllegalArgumentException("The amount of angles should be more than 2.");
        }
        fillPoints(center, r, n);
    }

    /**
     * Fills point for equilateral polygon
     * @param center center
     * @param r radius
     * @param n number of angles
     */
    private void fillPoints(Point2D center, double r, int n) {
        double angle = 2 * Math.PI / n;
        for(int i = 0; i < n; i++) {
            Point2D point2D = new Point2D();
            double x = center.getX() + r * Math.cos(angle * i);
            double y = center.getY() - r * Math.sin(angle * i);
            point2D.setX(x);
            point2D.setY(y);
            points.add(point2D);
        }
    }

    public Polygon2D() {
        this(new ArrayList<>());
    }

    public Polygon2D(List<Point2D> points) {
        this.points = points;
        shapeDrawer = new PolygonDrawer(this);
    }

    public List<Point2D> getPoints() {
        return points;
    }

    protected void setPoints(List<Point2D> points) {
        this.points = points;
    }

    @Override
    public ShapeDrawer getShapeDrawer() {
        return shapeDrawer;
    }

    @Override
    public String toString() {
        return "Polygon2D{" +
                "points=" + points +
                '}';
    }
}
