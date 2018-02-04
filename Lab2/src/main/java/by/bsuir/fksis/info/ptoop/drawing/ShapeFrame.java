package by.bsuir.fksis.info.ptoop.drawing;

import by.bsuir.fksis.info.ptoop.shape.Shape;
import by.bsuir.fksis.info.ptoop.shape.composite.ShapeList;
import by.bsuir.fksis.info.ptoop.util.ClassSearcher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShapeFrame extends JFrame {
    private ShapeList shapeList;
    private JPanel shapeDrawPanel;

    public ShapeList getShapeList() {
        return shapeList;
    }

    public void setShapeList(ShapeList shapeList) {
        this.shapeList = shapeList;
    }

    public ShapeFrame(String title) throws HeadlessException {
        this.shapeList = new ShapeList();
        this.setTitle(title);
        setConfig();
        setMainPanel();
    }

    private void setConfig() {
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void setMainPanel() {
        setDrawPanel();
        setShapeButtonPanel();
    }

    private void setDrawPanel() {
        shapeDrawPanel = new ShapeDrawPanel(shapeList);
        shapeDrawPanel.setPreferredSize(new Dimension(600, 500));
        add(shapeDrawPanel, BorderLayout.CENTER);
    }

    /**
     * Builds buttons for creating shapes
     */
    private void setShapeButtonPanel() {
        JPanel shapeButtonPanel = new JPanel();
        shapeButtonPanel.setBorder(BorderFactory.createEtchedBorder());
        shapeButtonPanel.setPreferredSize(new Dimension(200, 500));

        List<Class> shapeClasses = getShapeClasses();
        for (Class shapeClass : shapeClasses) {
            JButton jButton = new JButton(shapeClass.getSimpleName());
            jButton.setPreferredSize(new Dimension(150, 30));
            jButton.addActionListener(getButtonActionListener(shapeClass));
            shapeButtonPanel.add(jButton);
        }

        add(shapeButtonPanel, BorderLayout.EAST);
    }

    /**
     * Listener for each button which creates shape
     * @param shapeClass shape class for creating
     * @return new button listener
     */
    private ActionListener getButtonActionListener(Class shapeClass) {
        ActionListener actionListener = e -> {
            List<String> shapePropertiesNames;
            try {
                shapePropertiesNames = getShapePropertyNames(shapeClass);
            } catch (IllegalAccessException | NoSuchFieldException e1) {
                JOptionPane.showMessageDialog(this, "Shape inheritor should have 'propertyNames' field.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Map<String, JSpinner> shapeFields = new HashMap<>();
            buildShapeFieldsDialog(shapeFields, shapePropertiesNames);

            try {
                Shape newShape = buildNewShape(shapeClass, shapeFields);
                shapeList.add(newShape);
                shapeDrawPanel.repaint();
            } catch (NoSuchMethodException e1) {
                JOptionPane.showMessageDialog(this, "Shape inheritor should have 'constructShape' method.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalAccessException e1) {
                JOptionPane.showMessageDialog(this, "'constructShape' method should be public static.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (InvocationTargetException e1) {
                JOptionPane.showMessageDialog(this, e1, "Error", JOptionPane.ERROR_MESSAGE);
            }
        };
        return actionListener;
    }

    /**
     * Builds shape fields dialog and fills fields for the shape
     * @param shapeFields fields map with name
     * @param shapePropertiesNames list of properties names
     */
    private void buildShapeFieldsDialog(Map<String, JSpinner> shapeFields, List<String> shapePropertiesNames) {
        List<Component> windowFields = new ArrayList<>();
        for (String propertyName : shapePropertiesNames) {
            JLabel fieldLabel = new JLabel(propertyName);
            JSpinner numberSpinner = new JSpinner(new SpinnerNumberModel());
            shapeFields.put(propertyName, numberSpinner);
            windowFields.add(fieldLabel);
            windowFields.add(numberSpinner);
        }
        JOptionPane.showConfirmDialog(this, windowFields.toArray(), "Input values.", JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Builds new Shape
     * @param shapeClass shape class for creating
     * @param shapeFields fields with values for creating new shape
     * @return new Shape
     * @throws NoSuchMethodException throws if constructShape is not presented
     * @throws InvocationTargetException throws if constructShape throws new Exceptions
     * @throws IllegalAccessException throws if constructShape has no access
     */
    private Shape buildNewShape(Class shapeClass, Map<String, JSpinner> shapeFields) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method constructShapeMethod = shapeClass.getMethod("constructShape", Map.class);
        Map<String, Integer> shapeProperties = new HashMap<>();
        for (Map.Entry<String, JSpinner> shapeField : shapeFields.entrySet()) {
            shapeProperties.put(shapeField.getKey(), (Integer) shapeField.getValue().getValue());
        }
        return (Shape) constructShapeMethod.invoke(null, shapeProperties);
    }

    /**
     * Retrieves property names for Shape class
     * @param shapeName shape class
     * @return property names
     * @throws NoSuchFieldException throws if propertyNames is not presented
     * @throws IllegalAccessException throws if propertyNames has no access
     */
    private List<String> getShapePropertyNames(Class shapeName) throws NoSuchFieldException, IllegalAccessException {
        Field field = shapeName.getField("propertyNames");
        List<String> propertyNames = (List<String>) field.get(null);
        return propertyNames;
    }

    /**
     * Retrieves shape classes from the by.bsuir.fksis.info.ptoop.shape package
     * @return shape classes
     */
    private List<Class> getShapeClasses() {
        String shapePackageName = "by.bsuir.fksis.info.ptoop.shape";
        List<Class> shapeClasses = new ArrayList<>();
        try {
            shapeClasses = ClassSearcher.getClassesFromPackage(shapePackageName, Shape.class);
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(ShapeFrame.this, "The error while trying to extract classes from '" + shapePackageName + "' package", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return shapeClasses;
    }
}
