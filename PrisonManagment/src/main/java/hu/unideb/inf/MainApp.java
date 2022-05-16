package hu.unideb.inf;

import java.io.IOException;
import java.sql.SQLException;

import hu.unideb.inf.model.PrisonerPac.PrisonerDAO;
import hu.unideb.inf.model.WardenPac.WardenDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.h2.tools.Server;

public class MainApp extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/LOGIN.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Prison managment");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) throws SQLException {
        startDatabase();
        launch(args);
        stopDatabase();
    }


    private static Server s = new Server();

    private static void startDatabase() throws SQLException {
        s.runTool("-tcp", "-web", "-ifNotExists");
    }

    private static void stopDatabase()  {
        s.shutdown();
    }
}
