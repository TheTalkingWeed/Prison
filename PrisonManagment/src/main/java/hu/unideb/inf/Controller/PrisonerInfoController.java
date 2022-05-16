package hu.unideb.inf.Controller;

import hu.unideb.inf.model.PrisonerPac.Prisoner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;



public class PrisonerInfoController {
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
    void ShowData(ActionEvent event){
        prisonerInformationsOut(SceneController.temp);
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


}
