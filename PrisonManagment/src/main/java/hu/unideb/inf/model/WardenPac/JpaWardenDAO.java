package hu.unideb.inf.model.WardenPac;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

import static javax.persistence.Persistence.createEntityManagerFactory;

public class JpaWardenDAO implements WardenDAO{

    EntityManagerFactory entityManagerFactory = createEntityManagerFactory("br.com.fredericci.pu");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    @Override
    public void saveWarden(Warden a)  {
        entityManager.getTransaction().begin();
        entityManager.persist(a);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteWarden(Warden a) {
        entityManager.getTransaction().begin();
        entityManager.remove(a);
        entityManager.getTransaction().commit();
    }

    @Override
    public void updateWarden(Warden a) {
        saveWarden(a);
    }

    @Override
    public List<Warden> getWardens() {
        TypedQuery<Warden> query = entityManager.createQuery(
                "SELECT a FROM Warden a", Warden.class);
        List<Warden> wardens = query.getResultList();
        return wardens;
    }

    @Override
    public void close() throws Exception {
        entityManager.close();
        entityManagerFactory.close();
    }
}
