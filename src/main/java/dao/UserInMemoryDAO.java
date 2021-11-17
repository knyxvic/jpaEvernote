package dao;

import jakarta.enterprise.context.ApplicationScoped;
import model.User;

import java.util.List;

public class UserInMemoryDAO implements UserDAO{
    private static List<User> users = List.of(
            new User("Rincevent"),
            new User("Veterini"),
            new User("Vimaire")
            );
    @Override
    public List<User> getAll(){
        return List.copyOf(users);
    }

    public User getByLogin(String login) {
        User result = null;
        for(var user : users) {
            if(user.getLogin().equals(login)){
                result = user;
            }
        }
        return result;
    }

    @Override
    public void persist(User user) {

    }

    @Override
    public void deleteUser(User user) {

    }

    @Override
    public void updateLoginUser(User user) {
    }


}
