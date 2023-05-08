package uca.org.javafx.AdvancedColorChooser;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.converter.NumberStringConverter;
import javafx.util.StringConverter;

public class Controller {

    @FXML
    private TextField blueField;

    @FXML
    private Slider blueSlider;

    @FXML
    private TextField greenField;

    @FXML
    private Slider greenSlider;

    @FXML
    private TextField opacityField;

    @FXML
    private Slider opacitySlider;

    @FXML
    private TextField redField;

    @FXML
    private Slider redSlider;

    @FXML
    private Rectangle square;
    private DoubleProperty redSliderValue = new SimpleDoubleProperty();
    private DoubleProperty greenSliderValue = new SimpleDoubleProperty();
    private DoubleProperty blueSliderValue = new SimpleDoubleProperty();
    private DoubleProperty opacitySliderValue = new SimpleDoubleProperty();
    public void initialize() {
        StringConverter<Number> converter = new NumberStringConverter();
        Bindings.bindBidirectional(redField.textProperty(), redSlider.valueProperty(), converter);
        Bindings.bindBidirectional(greenField.textProperty(), greenSlider.valueProperty(), converter);
        Bindings.bindBidirectional(blueField.textProperty(), blueSlider.valueProperty(), converter);
        Bindings.bindBidirectional(opacityField.textProperty(), opacitySlider.valueProperty(), converter);
        Bindings.bindBidirectional(redSliderValue, redSlider.valueProperty());
        Bindings.bindBidirectional(greenSliderValue, greenSlider.valueProperty());
        Bindings.bindBidirectional(blueSliderValue, blueSlider.valueProperty());
        Bindings.bindBidirectional(opacitySliderValue, opacitySlider.valueProperty());

        redSliderValue.addListener((observable, oldValue, newValue) -> {
            changeColor();
        });
        greenSliderValue.addListener((observable, oldValue, newValue) -> {
            changeColor();
        });
        blueSliderValue.addListener((observable, oldValue, newValue) -> {
            changeColor();
        });
        opacitySliderValue.addListener((observable, oldValue, newValue) -> {
            changeColor();
        });
    }
    public void changeColor() {
        square.setFill(Color.rgb(redSliderValue.intValue(), greenSliderValue.intValue(), blueSliderValue.intValue(), opacitySliderValue.doubleValue()));
    }
}
