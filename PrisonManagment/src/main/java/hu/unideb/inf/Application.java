package hu.unideb.inf;

import java.sql.SQLException;
import java.time.LocalDate;

import hu.unideb.inf.model.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.h2.tools.Server;

public class Application extends javafx.application.Application {


    @Override
    public void start(Stage stage)  {
        try {
           // FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LoginScreen.fxml"));
            FXMLLoader loader = new FXMLLoader(Application.class.getResource("/fxml/LoginScreen.fxml"));
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


        try (PrisonerDAO pDAO = new JpaPrisonerDAO(); WardenDAO wDAO = new JpaWardenDAO(); ) {
            handleData(pDAO);
            handleData(wDAO);
        } catch (Exception e) {
            e.printStackTrace();
        }

        launch(args);
        stopDatabase();

    }


    public static void handleData(WardenDAO wDAO){

        Warden warden = new Warden();
        warden.setFname("Will");
        warden.setLname("Smith");
        warden.setJoinDate(LocalDate.of(2005,2,3));
        warden.setRank(Warden.Rank.SILVER_ELIT_MASTER);
        warden.setFloorInCharge(2);

        wDAO.saveWarden(warden);

    }

    public static void handleData(PrisonerDAO pDAO){
        Prisoner a = new Prisoner();
        a.setFname("Ben");
        a.setLname("Dover");
        a.setBornDate(LocalDate.of(1960,1,12));
        a.setAge();
        a.setEntrancedate(LocalDate.of(2010,1,5));
        a.setReleasedate(LocalDate.of(2015,1,5));
        a.setSecuritylvl(Prisoner.SecLevel.LOW);
        a.setCellnumber(102);
        a.setCrime(Prisoner.Crime.Stalking);

        Prisoner a2 = new Prisoner();
        a2.setFname("Jhon");
        a2.setLname("Smith");
        a2.setBornDate(LocalDate.of(1980,5,6));
        a2.setAge();
        a2.setEntrancedate(LocalDate.of(2000,5,14));
        a2.setReleasedate(LocalDate.of(2015,6,14));
        a2.setSecuritylvl(Prisoner.SecLevel.HIGH);
        a2.setCellnumber(101);
        a2.setCrime(Prisoner.Crime.Second_degree_Murder);


        pDAO.savePrisoner(a);
        pDAO.savePrisoner(a2);
    }

    private static Server s = new Server();

    private static void startDatabase() throws SQLException {
        s.runTool("-tcp", "-web", "-ifNotExists");
    }

    private static void stopDatabase()  {
        s.shutdown();
    }
}
