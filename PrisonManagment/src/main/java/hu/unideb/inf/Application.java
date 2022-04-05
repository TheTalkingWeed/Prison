package hu.unideb.inf;

import java.io.IOException;
import java.sql.SQLException;

import hu.unideb.inf.model.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.h2.tools.Server;

public class Application extends javafx.application.Application {


    @Override
    public void start(Stage stage)  {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LoginScreen.fxml"));
            Scene scene = new Scene(loader.load());
            stage.setTitle("PrisonManagment");
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            System.out.println("valami nem jó ");
        }
    }

    public static void main(String[] args) throws SQLException {
        startDatabase();

        //try-with-resources
        try (PrisonerDAO aDAO = new JpaPrisonerDAO();) {
            handleData(aDAO);
        } catch (Exception e) {
            e.printStackTrace();
        }

        launch(args);
        stopDatabase();

    }

    public static void handleData(PrisonerDAO aDAO){
        Prisoner a = new Prisoner();
        a.setName("Peppuska");
        a.setAge(4);
        a.setGender(Prisoner.GenderType.FEMALE);
        // aDAO.saveAnimal(a);

        Prisoner a2 = new Prisoner();
        a2.setName("Geri");
        a2.setAge(2);
        a2.setGender(Prisoner.GenderType.MALE);
        // aDAO.saveAnimal(a2);

       aDAO.saveAnimal(a);
       aDAO.saveAnimal(a2);
    }

    private static Server s = new Server();

    private static void startDatabase() throws SQLException {
        s.runTool("-tcp", "-web", "-ifNotExists");
    }

    private static void stopDatabase()  {
        s.shutdown();
    }
}
