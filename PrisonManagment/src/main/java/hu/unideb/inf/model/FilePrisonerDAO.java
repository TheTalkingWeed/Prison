package hu.unideb.inf.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FilePrisonerDAO implements PrisonerDAO {

    private List<Prisoner> prisoners;

    public FilePrisonerDAO() {
        //deserialization
        try (FileInputStream fis = new FileInputStream("animals.ser");
        ObjectInputStream ois = new ObjectInputStream(fis);){
            prisoners = (List<Prisoner>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            prisoners = new ArrayList<>();
        }
    }

    /**
     * Serializes the list of animals
     */
    private void serialize(){
        try (FileOutputStream fos = new FileOutputStream("animals.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);){
            oos.writeObject(prisoners);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveAnimal(Prisoner a) {
        if (!prisoners.contains(a)) prisoners.add(a);
        serialize();
    }

    @Override
    public void deleteAnimal(Prisoner a) {
        prisoners.remove(a);
        serialize();
    }

    @Override
    public void updateAnimal(Prisoner a) {
        prisoners.remove(a); //change the equals method of the Animal to have a proper working
        prisoners.add(a);
        serialize();
    }

    @Override
    public List<Prisoner> getAnimals() {
        return prisoners;
    }

    @Override
    public void close() throws Exception {
        serialize();
    }
}
