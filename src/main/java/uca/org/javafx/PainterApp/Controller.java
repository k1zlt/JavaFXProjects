package uca.org.javafx.PainterApp;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller {

    @FXML
    private Slider blueSlider;

    @FXML
    private Button clearButton;

    @FXML
    private Label colorLabel;

    @FXML
    private Pane drawingPane;

    @FXML
    private Slider greenSlider;

    @FXML
    private Slider opacitySlider;

    @FXML
    private Rectangle rectangle;

    @FXML
    private Slider redSlider;

    @FXML
    private ToggleGroup shapeGroup;

    @FXML
    private Slider sizeSlider;

    @FXML
    private Button undoButton;

    @FXML
    private RadioButton triangleShape, circleShape, squareShape, ellipseShape, polygonShape;

    private double penSize = 1;
    private Color brushColor;
    private int red = 0;
    private int green = 0;
    private int blue = 0;
    private double opacity = 1;

    public void initialize() {
        brushColor = Color.rgb(red, green, blue, opacity);
        redSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            red = newValue.intValue();
            brushColor = Color.rgb(red, green, blue, opacity);
            colorChanged();
        });
        greenSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            green = newValue.intValue();
            brushColor = Color.rgb(red, green, blue, opacity);
            colorChanged();
        });
        blueSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            blue = newValue.intValue();
            brushColor = Color.rgb(red, green, blue, opacity);
            colorChanged();
        });
        opacitySlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            opacity = (double)newValue;
            brushColor = Color.rgb(red, green, blue, opacity);
            colorChanged();
        });
        sizeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            penSize = (double)newValue;
        });
    }
    @FXML
    void onClear(ActionEvent event) {
        drawingPane.getChildren().clear();
    }

    @FXML
    void undoTheAction(ActionEvent event) {
        int i = drawingPane.getChildren().size();
        if (i > 0) {
            drawingPane.getChildren().remove(i - 1);
        }
    }
    @FXML
    void drawingAreaMouseDragged(MouseEvent e) {
        if (drawingPane.getLayoutX()>e.getX() || drawingPane.getLayoutX()+drawingPane.getWidth() < e.getX()
        || drawingPane.getLayoutY()>e.getY() || drawingPane.getLayoutY()+drawingPane.getHeight() < e.getY()) {
            return;
        }
        if (triangleShape.isSelected()) {
            Polygon t = new Polygon();
            t.getPoints().addAll(
                    e.getX(), e.getY() - penSize,
                    e.getX() + penSize, e.getY() + penSize,
                    e.getX() - penSize, e.getY() + penSize);
            t.setFill(brushColor);
            drawingPane.getChildren().add(t);
        } else if (circleShape.isSelected()) {
            Circle c = new Circle(e.getX(), e.getY(), penSize*5, brushColor);
            drawingPane.getChildren().add(c);
        } else if (squareShape.isSelected()) {
            Polygon s = new Polygon();
            s.getPoints().addAll(
                    e.getX()-penSize, e.getY()-penSize,
                    e.getX()-penSize, e.getY()+penSize,
                    e.getX()+penSize, e.getY()+penSize,
                    e.getX()+penSize, e.getY()-penSize
                    );
            s.setFill(brushColor);
            drawingPane.getChildren().add(s);
        } else if (ellipseShape.isSelected()) {
            Ellipse el = new Ellipse(e.getX(), e.getY(), penSize*1.5, penSize*0.5);
            el.setFill(brushColor);
            drawingPane.getChildren().add(el);
        } else if (polygonShape.isSelected()) {
            Polygon p = new Polygon();
            p.getPoints().addAll(
                    e.getX()-penSize,e.getY(),
                    e.getX()-penSize/2.0,e.getY()-penSize,
                    e.getX()+penSize/2.0,e.getY()-penSize,
                    e.getX()+penSize,e.getY(),
                    e.getX()+penSize/2.0,e.getY()+penSize,
                    e.getX()-penSize/2.0,e.getY()+penSize
            );
            drawingPane.getChildren().add(p);
        }

    }

    private void colorChanged() {
        rectangle.setFill(Color.rgb(red, green, blue, opacity));
        colorLabel.setText(String.format("(%d,%d,%d,%.2f)", red, green, blue, opacity));
    }
}
