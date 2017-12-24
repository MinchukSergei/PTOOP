package by.bsuir.fksis.info.ptoop.shape;

import by.bsuir.fksis.info.ptoop.util.Point2D;

import java.awt.*;
import java.util.Objects;

public class Line2D implements Shape {
    private Point2D p1;
    private Point2D p2;

    public Line2D() {
        this(new Point2D(), new Point2D());
    }

    public Line2D(Point2D p1, Point2D p2) {
        this.p1 = p1;
        this.p2 = p2;
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

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.draw(new java.awt.geom.Line2D.Double(p1.getX(), p1.getY(), p2.getX(), p2.getY()));
    }
}
