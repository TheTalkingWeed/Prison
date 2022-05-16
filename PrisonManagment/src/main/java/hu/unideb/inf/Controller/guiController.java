package hu.unideb.inf.Controller;

import hu.unideb.inf.model.Admin;
import hu.unideb.inf.model.AdminDAO;
import hu.unideb.inf.model.JpaAdminDAO;
import hu.unideb.inf.model.Prisoner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class guiController {
    @FXML
    private TextField CellNumber_textbox;

    @FXML
    private Button deleteButtonMP;

    @FXML
    private Button editWardenDetailsButton;

    @FXML
    private TextField prisonerFirstName_textbox;

    @FXML
    private TextField prisonerID_textbox;

    @FXML
    private TextField prisonerLastName_textbox;

    @FXML
    private Button saveButtonMP;

    @FXML
    private Button searchButtonMP;

    @FXML
    void DeleteButtonMP(ActionEvent event) {
    }
    }