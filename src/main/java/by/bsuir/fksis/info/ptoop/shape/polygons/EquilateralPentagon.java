package by.bsuir.fksis.info.ptoop.shape.polygons;

import by.bsuir.fksis.info.ptoop.util.Point2D;

public class EquilateralPentagon extends Polygon2D {

    public EquilateralPentagon(Point2D center, double R) {
        super(center, R, 5);
    }

    @Override
    public String toString() {
        return "EquilateralPentagon{" +
                "points=" + getPoints() +
                "}";
    }
}
