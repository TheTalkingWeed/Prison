package inf.unideb.prison;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
    private TextField username;

    @FXML
    private PasswordField password;

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

    @FXML
    void onLoginGuest(ActionEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AfterLoginScreen.fxml"));
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
    void onLogin(ActionEvent event) {
        try {
            String usernameinput = username.getText();
            String passwordinput = password.getText();
            if (usernameinput.equals("admin") && passwordinput.equals("admin")){
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AfterLoginScreen.fxml"));
                    Parent par1 =fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setResizable(false);
                    stage.setTitle("Prisoner");
                    stage.setScene(new Scene(par1));
                    stage.show();
                }catch (Exception e){
                    e.printStackTrace();
                }
            } else{
                System.out.println("Hibás felhasználónév vagy jelszó");
            }
        } catch (Exception e){
            e.printStackTrace();
        }



    }
}
