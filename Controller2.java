package it.univr.wordautoma;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

// Controller for Scene2
public class Controller2 {

    @FXML
    public Text mainTitle;

    @FXML
    private Label dataLabel;

    @FXML
    private TextField myText;

    public void initialize() {
        // Clear the focus from the text field initially
        myText.setFocusTraversable(false);

        // Add an event handler to set focus when the text field is clicked
        myText.setOnMouseClicked(event -> {
            myText.setFocusTraversable(true);
            myText.requestFocus();
        });
    }

    // Method to receive data from Scene1Controller
    public void setData(String data) {

        dataLabel.setText(data);
    }
}
