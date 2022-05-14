package hu.unideb.inf.model;

import java.util.List;

public interface PrisonerDAO extends AutoCloseable{

    public void savePrisoner(Prisoner a);
    public void deletePrisoner(Prisoner a);
    public void updatePrisoner(Prisoner a);
    public List<Prisoner> getPrisoners();

}
