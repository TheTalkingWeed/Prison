package inf.unideb.prison;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SceneController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}