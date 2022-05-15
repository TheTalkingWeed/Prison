package hu.unideb.inf.Controller;

import hu.unideb.inf.model.Admin;
import hu.unideb.inf.model.AdminDAO;
import hu.unideb.inf.model.JpaAdminDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class SceneController {

    private boolean isAdmin;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button loginAsadmin;

    @FXML
    private Button loginAsguest;

    @FXML
    void onLoginGuest(ActionEvent event){
        try {
            isAdmin = false;

            Alert alertwindow = new Alert(Alert.AlertType.INFORMATION);

            alertwindow.setTitle("Logged in successfully");
            alertwindow.setContentText("You logged in as Guest!");
            alertwindow.showAndWait();

            Stage stage2 = (Stage) loginAsguest.getScene().getWindow();
            stage2.close();

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
            AdminDAO adminDAO = new JpaAdminDAO();
            List<Admin> admins = new ArrayList<>(adminDAO.getAdmins());

            if(!containsUsername(admins,usernameinput))
            {
                Alert alertwindow = new Alert(Alert.AlertType.WARNING);

                alertwindow.setTitle("Hibás belépés");
                alertwindow.setContentText("Felhasználó nem található");
                alertwindow.show();



            }else if (isValid(admins,usernameinput,passwordinput)){
                try {
                    Alert alertwindow = new Alert(Alert.AlertType.INFORMATION);

                    alertwindow.setTitle("Logged in successfully");
                    alertwindow.setContentText("You logged in as Admin!");
                    alertwindow.showAndWait();

                    Stage stage2 = (Stage) loginAsadmin.getScene().getWindow();
                    stage2.close();
                    isAdmin = true;
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

    private boolean containsUsername(List<Admin> admins, String username){
        return admins.stream().filter(o -> o.getUsername().equals(username)).findFirst().isPresent();
    }

    private String getPasswordByUserName(List<Admin> admins, String username) {
        //return admins.stream().filter(o -> o.getPassword().equals(password)).findFirst().isPresent();

        for (Admin a : admins) {
            if (a.getUsername().equals(username)) {
                return a.getPassword();
            }
        }
        return null;
    }
    private boolean isValid(List<Admin> admins, String username,String password){



        if (getPasswordByUserName(admins,username).equals(password)) return true;

        return false;
    }
}