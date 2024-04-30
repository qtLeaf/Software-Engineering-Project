package it.univr.wordautoma;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Controller {

    @FXML
    public Text mainTitle;

    @FXML
    private ComboBox<String> nodeDropdown;

    @FXML
    private ComboBox<String> nodeDropdown2;

    @FXML
    public void onSubmit(javafx.event.ActionEvent event) throws IOException {
        // Load new scene
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().
                getResource("newScene.fxml")));

        // Set the new scene to the stage
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);
    }

    // Initialize method can be used to pass the stage reference
    public void initialize() {
        nodeDropdown.setOnAction(event -> nodeDropdown.setDisable(true));
        nodeDropdown2.setOnAction(event -> nodeDropdown2.setDisable(true));
    }

}

