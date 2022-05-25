package hu.unideb.inf.model.Prison;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class JpaPrisonDAO implements PrisonDAO{

    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("perfile");
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();


    @Override
    public void savePrison(Prison a) {
        entityManager.getTransaction().begin();
        entityManager.persist(a);
        entityManager.getTransaction().commit(); 
    }

    @Override
    public void deletePrison(Prison a) {
        entityManager.getTransaction().begin();
        entityManager.remove(a);
        entityManager.getTransaction().commit();
    }

    @Override
    public void updatePrison(Prison a) {
        savePrison(a);
    }

    @Override
    public List<Prison> getPrisons() {
        TypedQuery<Prison> query = entityManager.createQuery(
                "SELECT a FROM Prison a", Prison.class);
        List<Prison> prisons = query.getResultList();
        return prisons;
    }

    @Override
    public void close() throws Exception {
        entityManager.close();
        entityManagerFactory.close();
    }
}
