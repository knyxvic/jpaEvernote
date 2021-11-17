package controllers;

import dao.UserDAO;
import dao.UserInMemoryDAO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class UserControllerImpl implements UserController{
    @Inject
    private UserDAO userDAO;

    @Override
    @Transactional
    public User createUser(String login){
        var user = new User(login);
        userDAO.persist(user);
        return user;
    }

    @Override
    public List<User> getAll(){
        return userDAO.getAll();
    }

    @Override
    public Optional<User> getByLogin(String login){
        var maybeUser = userDAO.getByLogin(login);
        return Optional.ofNullable(maybeUser);
    }

    @Override
    @Transactional
    public void deleteUser(User user){
        userDAO.deleteUser(user);
    }


    /*@Override
    @Transactional
    public User updateLoginUser(UUID userId, String login) throws ExistentLoginException {
        if (userDAO.getByLogin(login) != null) {
            throw new ExistentLoginException();
        }

        userId.setLogin(login);
       // userDAO.updateLoginUser(user);

        return userId;
    }*/
}
