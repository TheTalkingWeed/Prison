package hu.unideb.inf.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class SceneController {


    @FXML
    private TextField username;

    @FXML
    private PasswordField password;



    @FXML
    void onLoginGuest(ActionEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/GUI.fxml"));
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
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/GUI.fxml"));
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