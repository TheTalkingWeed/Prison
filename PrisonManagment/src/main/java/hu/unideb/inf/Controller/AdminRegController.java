package hu.unideb.inf.Controller;

import hu.unideb.inf.model.AdminPac.Admin;
import hu.unideb.inf.model.AdminPac.AdminDAO;
import hu.unideb.inf.model.AdminPac.JpaAdminDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.awt.*;
import java.util.List;

public class AdminRegController {

    @FXML
    private Button AdminCancelButton;

    @FXML
    private Button AdminRegButton;

    @FXML
    private PasswordField AdminpassIn;

    @FXML
    private PasswordField AdminpassInputAgain;

    @FXML
    private TextField AdminUsernameIn;

    AdminDAO adminDAO = new JpaAdminDAO();



    @FXML
    public void AdminRegCancel(ActionEvent event) {
        Stage stage = (Stage) AdminCancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void AdminRegSave(javafx.event.ActionEvent event) {
        Admin adminin = new Admin();
        if (containsUsername(adminDAO.getAdmins(),AdminUsernameIn.getText())){
            Alert alertwindow = new Alert(Alert.AlertType.WARNING);
            alertwindow.setTitle("Warning!");
            alertwindow.setContentText("Username already exists!");
            alertwindow.showAndWait();
        }else if (AdminpassIn.getText().equals(AdminpassInputAgain.getText()) && !containsUsername(adminDAO.getAdmins(),AdminUsernameIn.getText())){
            adminin.setUsername(AdminUsernameIn.getText());
            adminin.setPassword(AdminpassIn.getText());
            adminDAO.saveAdmin(adminin);

            Alert alertwindow = new Alert(Alert.AlertType.INFORMATION);
            alertwindow.setTitle("Information");
            alertwindow.setContentText("Admin registered successfully");
            alertwindow.showAndWait();

            Stage stage = (Stage) AdminRegButton.getScene().getWindow();
            stage.close();

        } else if(AdminUsernameIn.getText().equals("")){
            Alert alertwindow = new Alert(Alert.AlertType.WARNING);
            alertwindow.setTitle("Warning!");
            alertwindow.setContentText("Please type a username!");
            alertwindow.showAndWait();
        }else if (AdminpassIn.getText().equals("") || AdminpassInputAgain.getText().equals("")){
            Alert alertwindow = new Alert(Alert.AlertType.WARNING);
            alertwindow.setTitle("Warning!");
            alertwindow.setContentText("Please fill all password fields");
            alertwindow.showAndWait();
        }else if (!AdminpassIn.getText().equals(AdminpassInputAgain.getText())){
            Alert alertwindow = new Alert(Alert.AlertType.WARNING);
            alertwindow.setTitle("Warning!");
            alertwindow.setContentText("Password aren't match!");
            alertwindow.showAndWait();
        }
    }

    private boolean containsUsername(List<Admin> admins, String username){
        return admins.stream().filter(o -> o.getUsername().equals(username)).findFirst().isPresent();
    }



}
