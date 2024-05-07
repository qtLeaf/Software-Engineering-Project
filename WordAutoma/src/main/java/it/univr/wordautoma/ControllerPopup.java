package it.univr.wordautoma;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerPopup {

    @FXML
    private TextField textInput;

    // Variable to store whether the user confirmed the action
    private boolean confirmed = false;

    // Reference to the popup stage
    private Stage popupStage;

    // Method to set the reference to the popup stage
    public void setPopupStage(Stage popupStage) {
        this.popupStage = popupStage;
    }

    // Retrieving the text input
    String inputText;

    // Method to handle confirmation button press in the popup
    @FXML
    private void onConfirm() {
        inputText = textInput.getText();
        // Check if inputText is not null and has a length greater than 0
        if (inputText != null && !inputText.isEmpty()) {
            // Check if inputText length is less than or equal to 10 and matches the pattern
            if (inputText.length() <= 10 && inputText.matches("[a-zA-Z0-9]+")) {
                confirmed = true;
                // Close the popup stage
                popupStage.close();
                // Show a confirmation dialog with the input text
                //showConfirmationAlert(inputText);
            } else {
                showErrorAlert();
            }
        }
    }

    public String getInputText(){
        return inputText;
    }

    /*
    // Method to display a confirmation alert
    private void showConfirmationAlert(String inputText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("You entered: " + inputText);
        alert.showAndWait();
    }*/

    // Method to display an error alert
    private void showErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Please enter only letters (uppercase or lowercase) and numbers with a maximum of 10 characters.");
        alert.showAndWait();
    }

    public boolean isConfirmed() {
        return confirmed;
    }
}
