package by.bsuir.fksis.info.ptoop.drawing.shape;

import by.bsuir.fksis.info.ptoop.util.Point2D;
import by.bsuir.fksis.info.ptoop.util.Polygon2D;

import java.awt.*;
import java.awt.geom.Path2D;
import java.util.List;

/**
 * Drawer for polygons
 */
public class PolygonDrawer implements ShapeDrawer {
    private Polygon2D polygon2D;

    public PolygonDrawer(Polygon2D polygon2D) {
        this.polygon2D = polygon2D;
    }

    public Polygon2D getPolygon2D() {
        return polygon2D;
    }

    public void setPolygon2D(Polygon2D polygon2D) {
        this.polygon2D = polygon2D;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        List<Point2D> points = polygon2D.getPoints();

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
