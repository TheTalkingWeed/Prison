package hu.unideb.inf.Controller;


import hu.unideb.inf.model.PrisonerPac.JpaPrisonerDAO;
import hu.unideb.inf.model.PrisonerPac.Prisoner;
import hu.unideb.inf.model.PrisonerPac.PrisonerDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class PrisonerInfoController implements Initializable {
    @FXML
    private Label CN_label;

    @FXML
    private Label C_label;

    @FXML
    private Label ED_label;

    @FXML
    private Label FN_label;

    @FXML
    private Label Id_label;

    @FXML
    private Label LN_label;

    @FXML
    private Label RD_label;

    @FXML
    private Label SL_label;

    @FXML
    private Button Close;





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Prisoner prout ;
        PrisonerDAO prisonerDAO = new JpaPrisonerDAO();
        prout = getPrisonerById(prisonerDAO.getPrisoners(), SceneController.idinforSearch);
        prisonerInformationsOut(prout);
    }

    private void prisonerInformationsOut(Prisoner p){
        Id_label.setText(Integer.toString(p.getUniqueID()));
        FN_label.setText(p.getFname());
        LN_label.setText(p.getLname());
        ED_label.setText(p.getEntrancedate().toString());
        RD_label.setText(p.getReleasedate().toString());
        SL_label.setText(p.getSecuritylvl());
        CN_label.setText(Integer.toString(p.getCellnumber()));
        C_label.setText(p.getCrime());
    }

    private Prisoner getPrisonerById(List<Prisoner> plist, int id){
        Prisoner pr = new Prisoner();

        for (Prisoner p:plist) {
            if (p.getUniqueID()==id) return p;
        }

        return pr;
    }

    @FXML
    void CloseButton(ActionEvent event) {
        Stage stage = (Stage) Close.getScene().getWindow();
        stage.close();
    }
}
