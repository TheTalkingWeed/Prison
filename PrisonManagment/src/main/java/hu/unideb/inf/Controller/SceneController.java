package hu.unideb.inf.Controller;

import hu.unideb.inf.Application;
import hu.unideb.inf.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;


public class SceneController implements Initializable {

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
    private TextField PrisonerID;

    @FXML
    private TextField Prisoner_FN;

    @FXML
    private TextField Prisoner_LN;

    @FXML
    private DatePicker EntranceDate;
    @FXML
    private ChoiceBox<String> SecLevel;
    @FXML
    private DatePicker ReleaseDate;
    @FXML
    private TextField Cell_Number;

    @FXML
    private ChoiceBox<String> Crime;

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
    void DeleteWarden(ActionEvent event) {
        if (isAdmin == true){


            TextInputDialog dialog = new TextInputDialog("");
            dialog.setTitle("Delete");
            dialog.setHeaderText("Wared delete");
            dialog.setContentText("Adja meg az ID-ját a Wardennek.");

// Traditional way to get the response value.
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                WardenDAO wardenDAO = new JpaWardenDAO();
                List<Warden> wardens = new ArrayList<>(wardenDAO.getWardens());
                for (Warden p : wardens) {
                    if (p.getUnique_ID() == Integer.parseInt(result.get()))
                        wardenDAO.deleteWarden(p);

                }
            }else {

                Alert alertwindow = new Alert(Alert.AlertType.WARNING);

                alertwindow.setTitle("WARNING!!");
                alertwindow.setContentText("You cannot modify as a Guset");
                alertwindow.showAndWait();
            }
}   }
    @FXML
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
        if (isAdmin == true){
            try (PrisonerDAO pdao = new JpaPrisonerDAO())
            {
                Prisoner prisoner = new Prisoner();
                prisoner.setUniqueID(Integer.parseInt(PrisonerID.getText()));
                prisoner.setFname(Prisoner_FN.getText());
                prisoner.setLname(Prisoner_LN.getText());
                prisoner.setEntrancedate(EntranceDate.getValue());
                prisoner.setReleasedate(ReleaseDate.getValue());
                prisoner.setSecuritylvl(SecLevel.getValue());
                prisoner.setCellnumber(Integer.parseInt(Cell_Number.getText()));
                prisoner.setCrime(Crime.getValue());
            }catch (Exception e){
                e.printStackTrace();
            }
    } else {

        Alert alertwindow = new Alert(Alert.AlertType.WARNING);

        alertwindow.setTitle("WARNING!!");
        alertwindow.setContentText("You cannot modify as a Guest");
        alertwindow.showAndWait();
    }
    }
    @FXML
    void DeleteButtonMP(ActionEvent event) {
        if (isAdmin == true){

            TextInputDialog dialog = new TextInputDialog("");
            dialog.setTitle("Delete");
            dialog.setHeaderText("Prisoner delete");
            dialog.setContentText("Adja meg az ID-ját a Prisonernek.");

// Traditional way to get the response value.
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()){
                PrisonerDAO prisonerDAO = new JpaPrisonerDAO();
                List <Prisoner>  prisoners = new ArrayList<>(prisonerDAO.getPrisoners());
                for (Prisoner p : prisoners)
                {
                 if (p.getUniqueID()== Integer.parseInt(result.get()))
                 prisonerDAO.deletePrisoner(p);
                }
            }

        } else {

            Alert alertwindow = new Alert(Alert.AlertType.WARNING);

            alertwindow.setTitle("WARNING!!");
            alertwindow.setContentText("You cannot modify as a Guset");
            alertwindow.showAndWait();
        }
    }

    @FXML
    void SearchButtonMP(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Search");
        dialog.setHeaderText("Prisoner search");
        dialog.setContentText("Adja meg a nevét a Prisonernek.");

// Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            PrisonerDAO prisonerDAO = new JpaPrisonerDAO();
            List<Prisoner> prisoners = new ArrayList<>(prisonerDAO.getPrisoners());
            for (Prisoner p : prisoners) {
                String prisonername = p.getFname() + " " + p.getLname();
                if (prisonername.equals(result.get())) {
                    try {
                        Stage stage = new Stage();
                        FXMLLoader loader = new FXMLLoader(Application.class.getResource("/fxml/PrisonInfo.fxml"));
                        Scene scene = new Scene(loader.load());
                        stage.setTitle("PrisonInfo");
                        stage.setScene(scene);
                        stage.show();
                    } catch (Exception e) {
                        System.out.println("valami nem jó ");
                    }
                }
            }


        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] rangok =   {"Őr","Kisfőnök","Nagyfőnök"};
        Warden_Rank.getItems().addAll(rangok);
        String[] floors = {"1","2","3","4","5","6"};
        Warden_Floor.getItems().addAll(floors);
        String[] crime = {"Emberőlés","Rablás", "Erőszak"};
        Crime.getItems().addAll(crime);
        String[] secLevel = {"Low","Medium", "High"};
        SecLevel.getItems().addAll(secLevel);

    }
    }