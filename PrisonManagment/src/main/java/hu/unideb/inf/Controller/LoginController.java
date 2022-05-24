package hu.unideb.inf.Controller;

import hu.unideb.inf.model.AdminPac.Admin;
import hu.unideb.inf.model.AdminPac.AdminDAO;
import hu.unideb.inf.model.AdminPac.JpaAdminDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class LoginController {


    public static boolean isAdmin;

    public static String loginttime;

    public static String loggedinuser;


    @FXML
    private Button loginAsGuest;

    @FXML
    private Button loginAsAdmin;

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    Image icon = new Image("/fxml/prison_icon.png");

    @FXML
    void onLoginGuest(ActionEvent event){
        loggedinuser="Guest";
        loginttime=LocalDate.now()+" "+ LocalTime.now();
        try {
            isAdmin = false;

            Alert alertwindow = new Alert(Alert.AlertType.INFORMATION);

            alertwindow.setTitle("Logged in successfully");
            alertwindow.setContentText("You logged in as Guest!");
            alertwindow.showAndWait();

            Stage stage2 = (Stage) loginAsGuest.getScene().getWindow();
            stage2.close();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/GUI.fxml"));
            Parent par1 =fxmlLoader.load();
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("Prison management");
            stage.getIcons().add(icon);
            stage.setScene(new Scene(par1));
            stage.show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void onLogin(ActionEvent event) {
        loggedinuser=username.getText();
        loginttime=LocalDate.now()+" "+ LocalTime.now();
        try {
            String usernameinput = username.getText();
            String passwordinput = password.getText();
            AdminDAO adminDAO = new JpaAdminDAO();
            List<Admin> admins ;

            admins = adminDAO.getAdmins();

            if(!containsUsername(admins,usernameinput))
            {
                Alert alertwindow = new Alert(Alert.AlertType.WARNING);

                alertwindow.setTitle("Hibás belépés");
                alertwindow.setContentText("Felhasználó nem található");
                alertwindow.show();



            }else if (isValid(admins,usernameinput,passwordinput)){
                try {
                    isAdmin=true;

                    Alert alertwindow = new Alert(Alert.AlertType.INFORMATION);

                    alertwindow.setTitle("Logged in successfully");
                    alertwindow.setContentText("You logged in as Admin!");
                    alertwindow.showAndWait();

                    Stage stage2 = (Stage) loginAsAdmin.getScene().getWindow();
                    stage2.close();

                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/GUI.fxml"));
                    Parent par1 =fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setResizable(false);
                    stage.setTitle("Prison management");
                    stage.getIcons().add(icon);
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
        return admins.stream().anyMatch(o -> o.getUsername().equals(username));
    }

    private String getPasswordByUserName(List<Admin> admins, String username) {

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
