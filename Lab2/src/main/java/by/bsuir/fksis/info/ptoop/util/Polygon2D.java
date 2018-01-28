package by.bsuir.fksis.info.ptoop.util;

import by.bsuir.fksis.info.ptoop.shape.Shape;

import java.awt.*;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.List;

public abstract class Polygon2D implements Shape {
    private List<Point2D> points;

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
    }

    public List<Point2D> getPoints() {
        return points;
    }

    protected void setPoints(List<Point2D> points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "Polygon2D{" +
                "points=" + points +
                '}';
    }

    public void draw(Graphics2D graphics2D) {
        if (points.isEmpty()) {
            return;
        }

        Path2D path = new Path2D.Double();
        Point2D firstPoint = points.get(0);
        path.moveTo(firstPoint.getX(), firstPoint.getY());
        for (int i = 1; i < points.size(); i++) {
            Point2D point2D = points.get(i);
            path.lineTo(point2D.getX(), point2D.getY());
        }

        path.closePath();
        graphics2D.draw(path);
    }
}
