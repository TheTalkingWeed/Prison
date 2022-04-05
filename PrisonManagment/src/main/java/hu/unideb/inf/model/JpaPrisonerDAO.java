package hu.unideb.inf.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class JpaPrisonerDAO implements PrisonerDAO {

     EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
     EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Override
    public void saveAnimal(Prisoner a) {
        entityManager.getTransaction().begin();
        entityManager.persist(a);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteAnimal(Prisoner a) {
        entityManager.getTransaction().begin();
        entityManager.remove(a);
        entityManager.getTransaction().commit();
    }

    @Override
    public void updateAnimal(Prisoner a) {
        /*entityManager.getTransaction().begin();
        entityManager.persist(a);
        entityManager.getTransaction().commit();*/
        saveAnimal(a);
    }

    @Override
    public List<Prisoner> getAnimals() {
        TypedQuery<Prisoner> query = entityManager.createQuery(
                "SELECT a FROM Prisoner a", Prisoner.class);
        List<Prisoner> prisoners = query.getResultList();
        return prisoners;
    }


    @Override
    public void close() throws Exception {
        entityManager.close();
        entityManagerFactory.close();
    }
}
