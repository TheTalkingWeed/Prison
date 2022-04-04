package inf.unideb.prison;

import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import org.h2.tools.Server;


public class Main extends Application {



    @Override
    public void start(Stage stage) {
        try{

            Parent root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
            Scene scene = new Scene(root);
            stage.setResizable(false);
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e){
            System.out.println("nincs");
        }

    }

    public static void main(String[] args) throws SQLException {

       // startDatabase();

        launch();
    }

//     private static void startDatabase() throws SQLException {
//         new Server().runTool("-tcp", "-web", "-ifNotExists");
//     }
}
