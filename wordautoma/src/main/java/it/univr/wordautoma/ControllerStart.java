package it.univr.wordautoma;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

import java.io.*;

// Controller for SceneStart
public class ControllerStart {

    @FXML
    public Text mainTitle;

    @FXML
    public ComboBox<String> onSubmitFile = new ComboBox<>();

    ObservableList<String> items = FXCollections.observableArrayList();

    ControllerPopup popupController;
    private String nomeFile;

    private final String PATH = "src/main/resources/it/univr/wordautoma/automas/";


    /* Funzione del bottone: crea un nuovo file se è stato selezionato "Nuovo Automa" o niente nel ComboBox
    altrimenti apre un automa già creato; infine passa alla prossima schermata*/
    @FXML
    public void onSubmitStart(ActionEvent event) {
        // Show confirmation popup
        if (fileNamerPopup()) {
            try {
                // Get new File name
                nomeFile = popupController.getInputText();

                // Load new scene
                FXMLLoader loader = new FXMLLoader(getClass().getResource("midScene.fxml"));
                Parent root = loader.load();

                ControllerMid is = loader.getController();
                is.setFileName(nomeFile);

                // Set the new scene to the stage
                Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                stage.getScene().setRoot(root);
                stage.setTitle("Word Automa ~ " + nomeFile);
            } catch (IOException e) {
                e.printStackTrace();
                // Handle exception if unable to load new scene
            }
        }
    }

    // Aggiunge un PopUp per nominare il nuovo automa
    private boolean fileNamerPopup() {
        try {
            // Load the popup FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("nameScene.fxml"));
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

    // Aggiunge al Combo Box i nomi del file
    @FXML
    public void addFileLines() {
        onSubmitFile.setItems(items);
        String addFileLabel= "Nuovo Automa";
        items.add(addFileLabel);
        try (BufferedReader reader = new BufferedReader(new FileReader
                (PATH + "filesName.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                items.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    // Inizializza il Combo Box quando il Controller viene eseguito
    public void initialize() {
        addFileLines();
    }


}