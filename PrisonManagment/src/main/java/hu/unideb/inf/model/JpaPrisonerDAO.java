package hu.unideb.inf.model.PrisonerPac;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class JpaPrisonerDAO implements PrisonerDAO {

     EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
     EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Override
    public void savePrisoner(Prisoner a) {
        entityManager.getTransaction().begin();
        entityManager.persist(a);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deletePrisoner(Prisoner a) {
        entityManager.getTransaction().begin();
        entityManager.remove(a);
        entityManager.getTransaction().commit();
    }

    @Override
    public void updatePrisoner(Prisoner a) {
        /*entityManager.getTransaction().begin();
        entityManager.persist(a);
        entityManager.getTransaction().commit();*/
        savePrisoner(a);
    }

    @Override
    public List<Prisoner> getPrisoners() {
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
