package by.bsuir.fksis.info.ptoop.shape;

import by.bsuir.fksis.info.ptoop.util.Point2D;
import by.bsuir.fksis.info.ptoop.util.Polygon2D;

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
