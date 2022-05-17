package hu.unideb.inf.model.WardenPac;

import java.util.List;

public interface WardenDAO extends AutoCloseable{
    public void saveWarden(Warden a);
    public void deleteWarden(Warden a);
    public void updateWarden(Warden a);
    public List<Warden> getWardens();
}
