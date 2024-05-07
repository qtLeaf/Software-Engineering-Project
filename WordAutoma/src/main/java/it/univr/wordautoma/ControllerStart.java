package it.univr.wordautoma;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
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

    ControllerPopup popupController;

    @FXML
    public void onSubmitStart(ActionEvent event) {
        // Show confirmation popup
        if (showConfirmationPopup()) {
            try {
                // Load new scene
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().
                        getResource("structure.fxml")));
                String nomeFile = popupController.getInputText();

                // Set the new scene to the stage
                Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                stage.getScene().setRoot(root);
            } catch (IOException e) {
                e.printStackTrace();
                // Handle exception if unable to load new scene
            }
        }
    }

    private boolean showConfirmationPopup() {
        try {
            // Load the popup FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("popup.fxml"));
            Parent root = loader.load();

            // Create the popup stage
            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setTitle("New Word Automa");
            popupStage.setScene(new Scene(root));

            // Get the controller of the popup
            popupController = loader.getController();
            popupController.setPopupStage(popupStage);

            // Show the popup and wait for confirmation
            popupStage.showAndWait();

            // Return the result of the confirmation
            return popupController.isConfirmed();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle exception if unable to load popup
            return false;
        }
    }

    @FXML
    public void onSubmitFile() throws IOException {

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
