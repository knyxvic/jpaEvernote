package controllers;

import model.User;

import java.util.List;
import java.util.Optional;

public interface UserController {
    User createUser(String login);
    List<model.User> getAll();
    Optional<User> getByLogin(String login);
    void deleteUser(User user);
    //User updateLoginUser(User user, String newLogin) throws ExistentLoginException;
}
