package hu.unideb.inf.model.LoginPac;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class JpaLoginDAO implements LoginDAO {

    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("perfile");
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Override
    public void saveLogin(Login a) {
        entityManager.getTransaction().begin();
        entityManager.persist(a);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteLogin(Login a) {
        entityManager.getTransaction().begin();
        entityManager.remove(a);
        entityManager.getTransaction().commit();
    }

    @Override
    public void updateLogin(Login a) {
        saveLogin(a);
    }

    @Override
    public List<Login> getLogins() {
        TypedQuery<Login> query = entityManager.createQuery(
                "SELECT a FROM Login a", Login.class);
        List<Login> logins = query.getResultList();
        return logins;
    }


    @Override
    public void close() throws Exception {
        entityManager.close();
        entityManagerFactory.close();
    }
}
