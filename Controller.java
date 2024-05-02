package it.univr.wordautoma;

import javafx.event.ActionEvent;
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
    public TextArea outputTextArea;

    @FXML
    private TextField promptNode;

    @FXML
    public TextField promptValue;

    @FXML
    private TextField promptNode2;

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
        promptNode.setOnAction(event -> promptNode.setDisable(false));
        promptValue.setOnAction(event -> promptValue.setDisable(false));
        promptNode2.setOnAction(event -> promptNode2.setDisable(false));
    }

    public void onSubmitLink(ActionEvent event) throws IOException {
        String selectedNode = promptNode.getText();
        String selectedValue = promptValue.getText();
        String selectedNode2 = promptNode2.getText();

        if (selectedNode != null && selectedNode2 != null) {
            System.out.println("Link submitted between nodes: " + selectedNode +
                    " and " + selectedNode2);
            System.out.println("The event was done correctly.");
            promptNode.clear();
            promptValue.clear();
            promptNode2.clear();
            String newText = "You added: " + selectedNode + " ~ " + selectedValue + " ~ " + selectedNode2 + "\n";

            // Append the new text to the existing text
            outputTextArea.appendText(newText);
        } else {
            System.out.println("Please select a node.");
        }

    }
}

