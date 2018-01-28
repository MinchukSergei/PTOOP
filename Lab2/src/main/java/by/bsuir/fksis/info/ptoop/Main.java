package by.bsuir.fksis.info.ptoop;

import by.bsuir.fksis.info.ptoop.drawing.ShapeFrame;
import by.bsuir.fksis.info.ptoop.shape.*;
import by.bsuir.fksis.info.ptoop.shape.Shape;
import by.bsuir.fksis.info.ptoop.shape.composite.ShapeList;
import by.bsuir.fksis.info.ptoop.shape.Rectangle;
import by.bsuir.fksis.info.ptoop.util.Point2D;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final List<Shape> shapes = new ArrayList<>();

    static {
        shapes.add(new Line2D(new Point2D(50, 50), new Point2D(60, 60)));
        shapes.add(new Triangle(new Point2D(80, 80), 15));
        shapes.add(new Rectangle(new Point2D(100, 100), new Point2D(130, 130)));
        shapes.add(new EquilateralPentagon(new Point2D(160, 160), 20));
        shapes.add(new EquilateralHeptagon(new Point2D(200, 200), 20));
        shapes.add(new Circle(30, new Point2D(230, 230)));
    }

    public static void main(String[] args) {
        Shape shapeList = new ShapeList(shapes);
        Frame shapeFrame = new ShapeFrame(shapeList);
        shapeFrame.setVisible(true);
    }
}
