package it.univr.wordautoma;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().
                getResource("structure.fxml")));
        primaryStage.setTitle("JavaFX App with FXML and CSS");
        Scene scene = new Scene(root, 800, 700);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().
                getResource("style.css")).toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
