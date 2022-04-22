package hu.unideb.inf.model;

import java.util.List;

public interface AdminDAO extends AutoCloseable{
    public void saveAdmin(Admin a);
    public void deleteAdmin(Admin a);
    public void updateAdmin(Admin a);
    public List<Admin> getAdmins();
}
