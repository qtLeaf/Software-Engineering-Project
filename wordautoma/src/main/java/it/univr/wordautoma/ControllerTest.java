package it.univr.wordautoma;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.time.LocalTime;

// Controller for Scene2
public class ControllerTest {

    @FXML
    public Text mainTitle;

    @FXML
    private Label dataLabel;

    @FXML
    private TextField myText;

    @FXML
    private ImageView prevImg;

    private final String PATH= "src/main/resources/it/univr/wordautoma/automas/";

    private String nomeFile="graph";//da fixare

    public void initialize() {
        Platform.runLater(this::uploadImage);
    }

    public void uploadImage() {
        LocalTime time = LocalTime.now();
        System.out.println("Current Time: " + time);
        Image newImage = new Image("file:" + PATH + nomeFile + ".png");
        prevImg.setImage(newImage);
    }

    public void setFileName(String nomeFile){
        this.nomeFile= nomeFile;
    }
}
