package hu.unideb.inf.model;

import java.util.List;

public interface PrisonerDAO extends AutoCloseable{
    //CRUD methods
    public void saveAnimal(Prisoner a); //C
    public void deleteAnimal(Prisoner a); //D
    public void updateAnimal(Prisoner a); //U
    public List<Prisoner> getAnimals(); //R

}
