package hu.unideb.inf.Controller;

import hu.unideb.inf.model.PrisonerPac.JpaPrisonerDAO;
import hu.unideb.inf.model.PrisonerPac.Prisoner;
import hu.unideb.inf.model.PrisonerPac.PrisonerDAO;
import hu.unideb.inf.model.Utils.FileUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PrisonerEditController implements Initializable {


    @FXML
    private TextField CellIn;

    @FXML
    private ChoiceBox<String> CrimeIn;

    @FXML
    private DatePicker EdateIn;

    @FXML
    private TextField FnameIn;

    @FXML
    private TextField LnameIn;

    @FXML
    private Label PrisonerID;

    @FXML
    private DatePicker RdateIn;

    @FXML
    private ChoiceBox<String> SeclvlIn;

    @FXML
    private Button SaveButton;

    @FXML
    private Button Cancle;

    PrisonerDAO prisonerDAO = new JpaPrisonerDAO();

    @FXML
    void SavePrisoner(ActionEvent event){
        Prisoner prout;
        prout = getPrisonerById(prisonerDAO.getPrisoners(), SceneController.idin);
        prout.setFname(FnameIn.getText());
        prout.setFname(LnameIn.getText());
        prout.setCellnumber(Integer.parseInt(CellIn.getText()));
        prout.setSecuritylvl(SeclvlIn.getValue());
        prout.setReleasedate(RdateIn.getValue());
        prout.setEntrancedate(EdateIn.getValue());
        prout.setCrime(CrimeIn.getValue());

        prisonerDAO.updatePrisoner(prout);

        Alert alertwindow = new Alert(Alert.AlertType.INFORMATION);
        alertwindow.setTitle("Information");
        alertwindow.setContentText("Save was successful");
        alertwindow.showAndWait();


        Stage stage2 = (Stage) SaveButton.getScene().getWindow();
        stage2.close();


    }

    @FXML
    void CancleButton(ActionEvent event){
        Stage stage2 = (Stage) Cancle.getScene().getWindow();
        stage2.close();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] sclvl =  {"Low","Medium","High"};
        SeclvlIn.getItems().addAll(sclvl);
        CrimeIn.getItems().addAll(FileUtils.readCrimes("crimes.txt"));


        Prisoner prout;
        prout = getPrisonerById(prisonerDAO.getPrisoners(), SceneController.idin);
        PrisonerID.setText(Integer.toString(prout.getUniqueID()));
        FnameIn.setText(prout.getFname());
        LnameIn.setText(prout.getLname());
        EdateIn.setValue(prout.getEntrancedate());
        RdateIn.setValue(prout.getReleasedate());
        CellIn.setText(Integer.toString(prout.getCellnumber()));
        SeclvlIn.setValue(prout.getSecuritylvl());
        CrimeIn.setValue(prout.getCrime());
    }

    private Prisoner getPrisonerById(List<Prisoner> plist,int id){
        Prisoner pr = new Prisoner();
        for (Prisoner p:plist) {
            if (p.getUniqueID()==id) return p;
        }

        return pr;
    }
}
