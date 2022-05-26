package hu.unideb.inf.Controller;

import hu.unideb.inf.model.AdminPac.Admin;
import hu.unideb.inf.model.AdminPac.AdminDAO;
import hu.unideb.inf.model.AdminPac.JpaAdminDAO;
import hu.unideb.inf.model.Prison.JpaPrisonDAO;
import hu.unideb.inf.model.Prison.Prison;
import hu.unideb.inf.model.Prison.PrisonDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LoginController implements Initializable {




    public static boolean isAdmin;

    public static String loginttime;

    public static String loggedinuser;

    @FXML
    private ChoiceBox<Prison> PrisonChoiceBox;

    @FXML
    private Button loginAsGuest;

    @FXML
    private Button loginAsAdmin;

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    public static String selectedPrison;
    public static int idOfSelectedPrison;


    Image icon = new Image("/icons/prison_icon.png");

    PrisonDAO prisonDAO = new JpaPrisonDAO();

    @FXML
    void onLoginGuest(ActionEvent event){
        loggedinuser="Guest";
        loginttime=LocalDate.now()+" "+ LocalTime.now();
        if (PrisonChoiceBox.getValue()==null){
            Alert alertwindow = new Alert(Alert.AlertType.INFORMATION);

            alertwindow.setTitle("Warning");
            alertwindow.setContentText("Please choose a prison to continue");
            alertwindow.showAndWait();
        }else{
            try {
                isAdmin = false;

                Alert alertwindow = new Alert(Alert.AlertType.INFORMATION);
                selectedPrison = PrisonChoiceBox.getValue().toString();
                idOfSelectedPrison = getPrisonId(selectedPrison);
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

    }

    @FXML
    void onLogin(ActionEvent event) {
        loggedinuser=username.getText();
        loginttime=LocalDate.now()+" "+ LocalTime.now();
        if (PrisonChoiceBox.getValue()==null){
            Alert alertwindow = new Alert(Alert.AlertType.INFORMATION);

            alertwindow.setTitle("Warning");
            alertwindow.setContentText("Please choose a prison to continue");
            alertwindow.showAndWait();
        }else {
            try {
                String usernameinput = username.getText();
                String passwordinput = password.getText();
                AdminDAO adminDAO = new JpaAdminDAO();
                List<Admin> admins ;
                selectedPrison = PrisonChoiceBox.getValue().toString();
                idOfSelectedPrison = getPrisonId(selectedPrison);
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Prison> prisons = new ArrayList<>(prisonDAO.getPrisons());
        PrisonChoiceBox.getItems().addAll(prisons);
    }

    public static int getPrisonId(String prisonName){
        int id=0;

        PrisonDAO prisonDAO = new JpaPrisonDAO();
        List<Prison> prisons = new ArrayList<>(prisonDAO.getPrisons());

        for (Prison p:prisons) {
            if (p.getPrisonName().equals(prisonName))
                return p.getId();
        }

        return id;
    }
}
