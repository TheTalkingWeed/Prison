package hu.unideb.inf.Controller;

import hu.unideb.inf.model.WardenPac.JpaWardenDAO;
import hu.unideb.inf.model.WardenPac.Warden;
import hu.unideb.inf.model.WardenPac.WardenDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class WardenInfoController implements Initializable {


        @FXML
        private Label FloorNum;

        @FXML
        private Label WardenFN;

        @FXML
        private Label WardenID;

        @FXML
        private Label WardenJD;

        @FXML
        private Label WardenLN;

        @FXML
        private Label WardenR;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Warden wout;
        WardenDAO wardenDAO = new JpaWardenDAO();
        wout = getWardenById(wardenDAO.getWardens(),SceneController.wardenIdforSearch);
        SetLableValues(wout);
        

    }
    
    private void SetLableValues(Warden w){
        
         FloorNum.setText(w.getFloorInCharge());
         WardenFN.setText(w.getFname());
         WardenID.setText(Integer.toString(w.getUnique_ID()));
         WardenJD.setText(w.getJoinDate().toString());
         WardenLN.setText(w.getLname());
         WardenR.setText(w.getRank());
    }

    private Warden getWardenById(List<Warden> plist, int id){
        Warden wd = new Warden();
        for (Warden w:plist) {
            if (w.getUnique_ID()==id) return w;
        }

        return wd;
    }
}
