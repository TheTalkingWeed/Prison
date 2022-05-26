package hu.unideb.inf.model.Prison;

import hu.unideb.inf.model.AdminPac.Admin;
import hu.unideb.inf.model.LoginPac.Login;
import hu.unideb.inf.model.PrisonerPac.Prisoner;
import hu.unideb.inf.model.WardenPac.Warden;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Prison {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String prisonName;
    private int floorNumber;


    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrisonName() {
        return prisonName;
    }

    public void setPrisonName(String prisonName) {
        this.prisonName = prisonName;
    }

    @Override
    public String toString() {
        return prisonName;
    }
}
