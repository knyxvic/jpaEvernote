package dao;
import model.User;

import java.util.List;

public interface UserDAO {
    List<User> getAll();
    User getByLogin(String login);
    void persist(User user);
    void deleteUser(User user);
    void updateLoginUser(User user);
}
