package it.univr.wordautoma;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalTime;

public class ControllerMid {

    private Graph graph;

    @FXML
    public TextArea outputTextArea;

    @FXML
    private TextField promptNode;

    @FXML
    public TextField promptValue;

    @FXML
    private TextField promptNode2;

    @FXML
    private ImageView prevImg;

    private final String PATH= "src/main/resources/it/univr/wordautoma/automas/";

    private String nomeFile;//da fixare

    // Funzione del bottone "Submit", passa alla prossima schermata per impostonado alcuni valori
    @FXML
    public void onSubmit(javafx.event.ActionEvent event) throws IOException {// nuova scena

        FXMLLoader loader = new FXMLLoader(getClass().getResource("testScene.fxml"));
        Parent root = loader.load();

        ControllerTest is = loader.getController();
        is.setFileName(nomeFile);

        // Set the new scene to the stage
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);
    }

    // Initialize method can be used to pass the stage reference
    public void initialize() {
        Platform.runLater(() -> {
            graph= new Graph(nomeFile);//temporaneo
            promptNode.setOnAction(event -> promptNode.setDisable(false));
            promptValue.setOnAction(event -> promptValue.setDisable(false));
            promptNode2.setOnAction(event -> promptNode2.setDisable(false));
            updateImage();


        });

    }

    public void onSubmitLink() {
        String selectedNode = promptNode.getText();
        String selectedValue = promptValue.getText();
        String selectedNode2 = promptNode2.getText();

        if (selectedNode != null && selectedNode2 != null) {
            System.out.println("Link submitted between nodes: " + selectedNode +
                    " and " + selectedNode2 + " value " + selectedValue);
            System.out.println("The event was done correctly.");
            promptNode.clear();
            promptValue.clear();
            promptNode2.clear();
            String newText = "You added: " + selectedNode + " ~ " + selectedValue + " ~ " + selectedNode2 + "\n";

            graph.addArrow(selectedNode, selectedValue, selectedNode2);
            updateImage();
            // Append the new text to the existing text
            outputTextArea.appendText(newText);
        } else {
            System.out.println("Please select a node.");
        }

    }

    public void updateImage() {
        LocalTime time = LocalTime.now();
        System.out.println("Current Time: " + time);
        Image newImage = new Image("file:" + PATH + nomeFile + ".png");
        prevImg.setImage(newImage);
    }

    public void setFileName(String nomeFile){
        this.nomeFile= nomeFile;
    }
}