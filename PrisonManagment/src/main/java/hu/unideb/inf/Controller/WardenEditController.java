package hu.unideb.inf.Controller;

import hu.unideb.inf.model.WardenPac.JpaWardenDAO;
import hu.unideb.inf.model.WardenPac.Warden;
import hu.unideb.inf.model.WardenPac.WardenDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class WardenEditController implements Initializable {
    @FXML
    private ChoiceBox<String> Floor;

    @FXML
    private TextField FnameIn;

    @FXML
    private DatePicker JDin;

    @FXML
    private TextField LnameIn;

    @FXML
    private ChoiceBox<String> RankIn;

    @FXML
    private Button WardenCancel;

    @FXML
    private Label WardenIdForEdit;

    @FXML
    private Button WardenSave;

    WardenDAO wardenDAO = new JpaWardenDAO();

    @FXML
    void WardenCancelButton(ActionEvent event) {
        Stage stage = (Stage) WardenCancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    void WardenSaveButton(ActionEvent event) {
        Warden wout;
        wout = getWardenById(wardenDAO.getWardens(),SceneController.wardenIdIn);
        wout.setFname(FnameIn.getText());
        wout.setLname(LnameIn.getText());
        wout.setJoinDate(JDin.getValue());
        wout.setRank(RankIn.getValue());
        wout.setFloorInCharge(Floor.getValue());

        wardenDAO.updateWarden(wout);

        Alert alertwindow = new Alert(Alert.AlertType.INFORMATION);
        alertwindow.setTitle("Information");
        alertwindow.setContentText("Save was successful");
        alertwindow.showAndWait();

        Stage stage = (Stage) WardenSave.getScene().getWindow();
        stage.close();
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Warden wout;
        wout = getWardenById(wardenDAO.getWardens(),SceneController.wardenIdIn);
        String[] floors = {"1","2","3","4","5","6"};
        String[] rangok =   {"Guard","Small chief","Big chief"};
        WardenIdForEdit.setText(Integer.toString(SceneController.wardenIdIn));
        Floor.getItems().addAll(floors);
        FnameIn.setText(wout.getFname());
        JDin.setValue(wout.getJoinDate());
        LnameIn.setText(wout.getLname());
        RankIn.getItems().addAll(rangok);
        RankIn.setValue(wout.getRank());
        Floor.setValue(wout.getFloorInCharge());



    }

    private Warden getWardenById(List<Warden> plist, int id){
        Warden wd = new Warden();
        for (Warden w:plist) {
            if (w.getUnique_ID()==id) return w;
        }

        return wd;
    }
}
