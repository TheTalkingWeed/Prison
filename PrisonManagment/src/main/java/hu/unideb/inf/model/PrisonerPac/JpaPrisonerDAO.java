package hu.unideb.inf.model.PrisonerPac;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

import static javax.persistence.Persistence.createEntityManagerFactory;

public class JpaPrisonerDAO implements PrisonerDAO {

    private final EntityManagerFactory entityManagerFactory = createEntityManagerFactory("perfile");
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();

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
