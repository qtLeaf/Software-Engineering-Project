package it.univr.wordautoma;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

// Controller for Scene2
public class Controller2 {

    @FXML
    public Text mainTitle;

    @FXML
    public ComboBox nodeDropdown2;

    @FXML
    private Label dataLabel;

    // Method to receive data from Scene1Controller
    public void setData(String data) {

        dataLabel.setText(data);
    }
}
