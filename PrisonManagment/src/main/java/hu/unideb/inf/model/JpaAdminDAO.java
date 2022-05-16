package hu.unideb.inf.model.AdminPac;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class JpaAdminDAO implements AdminDAO{

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
    EntityManager entityManager = entityManagerFactory.createEntityManager();

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
    public void close() throws Exception {
        entityManager.close();
        entityManagerFactory.close();
    }
}
