package hu.unideb.inf;


import javafx.scene.image.Image;


import java.io.IOException;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.h2.tools.Server;


public class MainApp extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        Image img = new Image("/icons/login_icon.png");

        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/LOGIN.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Log in");
        stage.getIcons().add(img);
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) throws SQLException {
        startDatabase();

      //  DataUpload.uploadPrisons();
//        try (PrisonDAO prisonDAO = new JpaPrisonDAO();){
//            Random rand = new Random();
//
//            List<Prison> temp = new ArrayList<>();
//
//            for (Prison p:temp) {
//                p.setFloorNumber(rand.nextInt(10));
//                prisonDAO.updatePrison(p);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }

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
