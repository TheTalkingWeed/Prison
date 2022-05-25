package hu.unideb.inf.model.AdminPac;

import hu.unideb.inf.model.Prison.Prison;

import java.util.List;

public interface AdminDAO extends AutoCloseable{
    public void saveAdmin(Admin a);
    public void deleteAdmin(Admin a);
    public void updateAdmin(Admin a);
    public List<Admin> getAdmins();
    public default void savePrison(Prison prison){
        throw new UnsupportedOperationException();
    }
}
