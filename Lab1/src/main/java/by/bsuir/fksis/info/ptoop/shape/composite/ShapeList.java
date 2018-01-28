package by.bsuir.fksis.info.ptoop.shape.composite;

import by.bsuir.fksis.info.ptoop.shape.Shape;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class ShapeList implements Shape {
    private List<Shape> shapeList;

    public ShapeList() {
        this.shapeList = new ArrayList<>();
    }

    public ShapeList(List<Shape> shapeList) {
        this.shapeList = shapeList;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        for (Shape shape : shapeList) {
            shape.draw(graphics2D);
        }
    }
}
