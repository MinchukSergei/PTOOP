package by.bsuir.fksis.info.ptoop.shape;

import by.bsuir.fksis.info.ptoop.util.Point2D;
import by.bsuir.fksis.info.ptoop.util.Polygon2D;

public class EquilateralHeptagon extends Polygon2D {

    public EquilateralHeptagon(Point2D center, double R) {
        super(center, R, 7);
    }

    @Override
    public String toString() {
        return "EquilateralHeptagon{" +
                "points=" + getPoints() +
                "}";
    }
}
