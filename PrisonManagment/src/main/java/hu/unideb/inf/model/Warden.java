package hu.unideb.inf.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Warden {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String Fname;
    private String Lname;
    private LocalDate JoinDate;
    @Enumerated(EnumType.STRING)
    private Rank rank;
    private int FloorInCharge;

    public int getFloorInCharge() {
        return FloorInCharge;
    }

    public void setFloorInCharge(int floorInCharge) {
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

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public enum Rank{
        SILVER_ELIT,
        SILVER_ELIT_MASTER,
        GOLD_NOVA_MASTER,
        MASTER_GUIARDIAN,
        DISTINGUISHED_MASTER_GUARDIAN,
        LEGENDARY_EAGLE_MASTER,
        GLOBAL_ELITE
    }
}
