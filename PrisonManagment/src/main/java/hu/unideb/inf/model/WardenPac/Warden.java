package hu.unideb.inf.model.WardenPac;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Warden {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int Unique_ID;
    private String Fname;
    private String Lname;
    private LocalDate JoinDate;
    private String rank;
    private String FloorInCharge;

    public String getFloorInCharge() {
        return FloorInCharge;
    }

    public void setFloorInCharge(String floorInCharge) {
        FloorInCharge = floorInCharge;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return Fname;
    }

    public void setFname(String fname) {
        Fname = fname;
    }

    public String getLname() {
        return Lname;
    }

    public void setLname(String lname) {
        Lname = lname;
    }

    public LocalDate getJoinDate() {
        return JoinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        JoinDate = joinDate;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public int getUnique_ID() {
        return Unique_ID;
    }

    public void setUnique_ID(int unique_ID) {
        Unique_ID = unique_ID;
    }
}
