package uca.org.javafx.TipCalculatorModification;

import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

import java.math.BigDecimal;
import java.math.MathContext;

public class Controller {

    @FXML
    private TextField amountField;

    @FXML
    private TextField tipField;

    @FXML
    private Slider tipSlider;

    @FXML
    private TextField totalField;

    @FXML
    private Label tipLabel;

    public void initialize() {
        tipLabel.textProperty().bind(Bindings.format("%.0f%%", tipSlider.valueProperty()));
        tipSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                calc();
            }
        });
        amountField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                calc();
            }
        });
    }
    void calc() {
        try {
            BigDecimal a = new BigDecimal(amountField.getText());
            BigDecimal p = new BigDecimal(tipSlider.getValue());
            BigDecimal tip = a.multiply(p).divide(BigDecimal.valueOf(100)).round(new MathContext(4));
            tipField.setText(tip.toString());
            totalField.setText(a.add(tip).toString());
        } catch (Exception e) {
            System.out.println("Something's wrong, I can feel it (six minutes, Slim Shady, you're on!)\n" +
                    "Just a feeling I've got, like something's about to happen, but I don't know what");
        }
    }
}
