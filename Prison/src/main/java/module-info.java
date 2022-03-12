module inf.unideb.prison {
    requires javafx.controls;
    requires javafx.fxml;


    opens inf.unideb.prison to javafx.fxml;
    exports inf.unideb.prison;
}