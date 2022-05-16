package hu.unideb.inf.Controller;

import hu.unideb.inf.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;
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
    @FXML
    private Button DeleteWarden_Buttons;

    @FXML
    private Button SaveWarden_Button;

    @FXML
    private Button deleteButtonMP;

    @FXML
    private TextField prisonerID_textbox;

    @FXML
    private TextField prisonerName_textbox;

    @FXML
    private TextField prisonerName_textbox1;

    @FXML
    private TextField prisonerName_textbox11;

    @FXML
    private Button saveButtonMP;

    @FXML
    private Button searchButtonMP;

    @FXML
    private TextField WardenID;

    @FXML
    private TextField Warden_FN;

    @FXML
    private ChoiceBox<String> Warden_Floor;

    @FXML
    private DatePicker Warden_JD;

    @FXML
    private TextField Warden_LN;
    @FXML
    private ChoiceBox<String> Warden_Rank;
    @FXML
    void DeleteButtonMP(ActionEvent event) {

    }

    @FXML
    void DeleteWarden(ActionEvent event) {
        if (isAdmin == true){

        } else {

            Alert alertwindow = new Alert(Alert.AlertType.WARNING);

            alertwindow.setTitle("WARNING!!");
            alertwindow.setContentText("You cannot modify as a Guset");
            alertwindow.showAndWait();
    }

}    @FXML
    void SaveWarden(ActionEvent event) {
        if (isAdmin == true){
           String[] rangok =   {"Őr","Kisfőnök","Nagyfőnök"};
            Warden_Rank.getItems().addAll(rangok);
           String[] floors = {"1","2","3","4","5","6"};
            WardenDAO wdao = new JpaWardenDAO();
            Warden warden = new Warden();
            Warden_Floor.getItems().addAll(floors);

        warden.setFname(Warden_FN.getText());
        warden.setLname(Warden_LN.getText());
        warden.setJoinDate(Warden_JD.getValue());
        warden.setRank(Warden_Rank.getValue());
        warden.setFloorInCharge(Warden_Floor.getValue());

        wdao.saveWarden(warden);


        } else {

            Alert alertwindow = new Alert(Alert.AlertType.WARNING);

            alertwindow.setTitle("WARNING!!");
            alertwindow.setContentText("You cannot modify as a Guset");
            alertwindow.showAndWait();
        }

    }
    @FXML
    void SaveMP(ActionEvent event) {

    }



    @FXML
    void SearchButtonMP(ActionEvent event) {

    }
}