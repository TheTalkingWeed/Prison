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
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "prison_name")
    private List<Prisoner> prisoners = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "prison_name")
    private List<Warden> wardens = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "prison_name")
    private List<Login> logins = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "prison_name")
    private List<Admin> admins = new ArrayList<>();

    public List<Admin> getAdmins() {
        return admins;
    }

    public void setAdmins(List<Admin> admins) {
        this.admins = admins;
    }

    public List<Prisoner> getPrisoners() {
        return prisoners;
    }

    public void setPrisoners(List<Prisoner> prisoners) {
        this.prisoners = prisoners;
    }

    public List<Warden> getWardens() {
        return wardens;
    }

    public void setWardens(List<Warden> wardens) {
        this.wardens = wardens;
    }

    public List<Login> getLogins() {
        return logins;
    }

    public void setLogins(List<Login> logins) {
        this.logins = logins;
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
