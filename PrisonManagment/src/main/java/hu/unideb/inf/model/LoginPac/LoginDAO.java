package hu.unideb.inf.model.LoginPac;

import java.util.List;

public interface LoginDAO extends AutoCloseable {
    public void saveLogin(Login a);
    public void deleteLogin(Login a);
    public void updateLogin(Login a);
    public List<Login> getLogins();

}
