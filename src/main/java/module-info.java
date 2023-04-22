module uca.org.javafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens uca.org.javafx to javafx.fxml;
    exports uca.org.javafx;

    opens uca.org.javafx.TipCalculator to javafx.fxml;
    exports uca.org.javafx.TipCalculator;
}