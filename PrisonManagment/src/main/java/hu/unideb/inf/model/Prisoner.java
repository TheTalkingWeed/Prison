package hu.unideb.inf.model.PrisonerPac;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

@Entity
public class Prisoner implements Serializable {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private  int UniqueID;
    private String Fname;
    private String Lname;
    private int age;
    private LocalDate BornDate;
    private LocalDate entrancedate;
    private LocalDate releasedate;
    private String securitylvl;
    private int cellnumber;
    private String crime;

    public int getAge() {
        return age;
    }

    public void setAge() {
        LocalDate today = LocalDate.now();
        this.age = Period.between(getBornDate(),today).getYears();
    }

    public LocalDate getBornDate() {
        return BornDate;
    }

    public void setBornDate(LocalDate bornDate) {
        BornDate = bornDate;
    }



    public int getCellnumber() {
        return cellnumber;
    }

    public void setCellnumber(int cellnumber) {
        this.cellnumber = cellnumber;
    }

    public String getSecuritylvl() {
        return securitylvl;
    }

    public void setSecuritylvl(String securitylvl) {
        this.securitylvl = securitylvl;
    }

    public String getCrime() {
        return crime;
    }

    public void setCrime(String crime) {
        this.crime = crime;
    }

    public LocalDate getEntrancedate() {
        return entrancedate;
    }

    public void setEntrancedate(LocalDate entrancedate) {

        this.entrancedate = entrancedate;
    }

    public LocalDate getReleasedate() {
        return releasedate;
    }

    public void setReleasedate(LocalDate releasedate) {
        this.releasedate = releasedate;
    }


    public int getUniqueID() {
        return UniqueID;
    }

    public void setUniqueID(int uniqueID) {
        UniqueID = uniqueID;
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

    @Override
    public String toString() {
        return "Prisoner{" +
                "id=" + id +
                ", UniqueID=" + UniqueID +
                ", Fname='" + Fname + '\'' +
                ", Lname='" + Lname + '\'' +
                ", age=" + age +
                ", BornDate=" + BornDate +
                ", entrancedate=" + entrancedate +
                ", releasedate=" + releasedate +
                ", securitylvl='" + securitylvl + '\'' +
                ", cellnumber=" + cellnumber +
                ", crime='" + crime + '\'' +
                '}';
    }
}
