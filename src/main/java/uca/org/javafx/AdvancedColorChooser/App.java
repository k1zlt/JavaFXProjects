package uca.org.javafx.AdvancedColorChooser;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import uca.org.javafx.HelloApplication;

import java.io.IOException;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AdvancedColorChooser.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 459, 150);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}
