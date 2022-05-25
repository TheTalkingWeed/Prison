package hu.unideb.inf.model.AdminPac;

import hu.unideb.inf.model.Prison.Prison;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

import static javax.persistence.Persistence.createEntityManagerFactory;


public class JpaAdminDAO implements AdminDAO{

    private final EntityManagerFactory entityManagerFactory = createEntityManagerFactory("perfile");
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Override
    public void saveAdmin(Admin a) {
        entityManager.getTransaction().begin();
        entityManager.persist(a);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteAdmin(Admin a) {
        entityManager.getTransaction().begin();
        entityManager.remove(a);
        entityManager.getTransaction().commit();
    }

    @Override
    public void updateAdmin(Admin a) {
        saveAdmin(a);
    }

    @Override
    public List<Admin> getAdmins() {
        TypedQuery<Admin> query = entityManager.createQuery(
                "SELECT a FROM Admin a", Admin.class);
        List<Admin> admins = query.getResultList();
        return admins;
    }

    @Override
    public void savePrison(Prison prison) {
        entityManager.getTransaction().begin();
        entityManager.persist(prison);
        entityManager.getTransaction().commit();
    }

    @Override
    public void close() throws Exception {
        entityManager.close();
        entityManagerFactory.close();
    }
}
