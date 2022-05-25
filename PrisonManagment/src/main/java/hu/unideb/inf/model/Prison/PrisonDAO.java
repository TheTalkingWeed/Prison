package hu.unideb.inf.model.Prison;

import hu.unideb.inf.model.LoginPac.Login;

import java.util.List;

public interface PrisonDAO extends AutoCloseable{
    public void savePrison(Prison a);
    public void deletePrison(Prison a);
    public void updatePrison(Prison a);
    public List<Prison> getPrisons();
}
