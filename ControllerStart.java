package it.univr.wordautoma;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

import java.io.*;

// Controller for SceneStart
public class ControllerStart {

    @FXML
    public Text mainTitle;

    @FXML
    public ComboBox<String> onSubmitFile;

    private final String PATH= "src/main/resources/it/univr/wordautoma/automas/";


    public void initialize(){
    }

    public void onSubmitStart(javafx.event.ActionEvent event) throws IOException {
        // Load new scene
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().
                getResource("structure.fxml")));

        // Set the new scene to the stage
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);
    }

    public void onSubmitFile(ActionEvent actionEvent) throws IOException {

        try (BufferedReader reader = new BufferedReader(new FileReader
                (PATH + "filesname.txt"))) {
            while (reader.readLine() != null) {
                onSubmitFile.getItems().addAll();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
