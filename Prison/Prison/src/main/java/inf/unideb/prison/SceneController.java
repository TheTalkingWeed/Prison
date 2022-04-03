package inf.unideb.prison;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import javax.xml.transform.Source;


public class SceneController {
    @FXML
    private Button prisonReportButton;

    @FXML
    private Button prisonerButton;

    @FXML
    private Button wardenButton;

    @FXML
    private Button wardenReportButton;

    @FXML
    void onPrisonerButton(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ManagingPrisoner.fxml"));
            Parent par1 =fxmlLoader.load();
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("Prisoner");
            stage.setScene(new Scene(par1));
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void onWardenPush(ActionEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("WardenDetails.fxml"));
            Parent par1 =fxmlLoader.load();
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("Prisoner");
            stage.setScene(new Scene(par1));
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}