module inf.unideb.prison {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.h2database;


    opens inf.unideb.prison to javafx.fxml;
    exports inf.unideb.prison;
}