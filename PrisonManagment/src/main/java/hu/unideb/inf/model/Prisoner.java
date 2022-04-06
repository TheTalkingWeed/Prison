package hu.unideb.inf.model;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;

@Entity
public class Prisoner implements Serializable {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String Fname;
    private String Lname;
    private int age;
    private LocalDate BornDate;
    private LocalDate entrancedate;
    private LocalDate releasedate;
    @Enumerated(EnumType.STRING)
    private SecLevel securitylvl;
    private int cellnumber;
    @Enumerated(EnumType.STRING)
    private Crime crime;

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

    public Crime getCrime() {
        return crime;
    }

    public void setCrime(Crime crime) {
        this.crime = crime;
    }

    public int getCellnumber() {
        return cellnumber;
    }

    public void setCellnumber(int cellnumber) {
        this.cellnumber = cellnumber;
    }

    public SecLevel getSecuritylvl() {
        return securitylvl;
    }

    public void setSecuritylvl(SecLevel securitylvl) {
        this.securitylvl = securitylvl;
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

    public enum SecLevel{
        HIGH,LOW,MID
    }

    public enum Crime{
        Aggravated_Assault,
        Child_Pornography,
        Child_Abuse,
        Computer_Crime,
        Drug_Manufacturing,
        Drug_Possession,
        Robbery,
        Rape,
        Shoplifting,
        Homicide,
        Harassment,
        First_degree_murder,
        Second_degree_Murder,
        Prostitution,
        Stalking,
        Tax_Evasion,
        Vandalism,
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




}
