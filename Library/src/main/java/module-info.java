module hu.alex.library.library {
    requires javafx.controls;
    requires javafx.fxml;


    opens hu.alex.library.library to javafx.fxml;
    exports hu.alex.library.library;
}