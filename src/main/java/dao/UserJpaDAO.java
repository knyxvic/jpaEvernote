package dao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import model.User;

import java.util.List;

@ApplicationScoped
public class UserJpaDAO implements UserDAO{

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<User> getAll() {
        var jpql = "SELECT u FROM User u";
        return em.createQuery(jpql, User.class)
                .getResultList();
    }

    @Override
    public User getByLogin(String login) {
        var jpql = "SELECT u FROM User u WHERE u.login = :login";
        try {
            return em.createQuery(jpql, User.class)
                    .setParameter(":login", login)
                    .getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    @Override
    public void persist(User user) {
        em.persist(user);
    }

    @Override
    public void deleteUser(User user) {
        em.remove(user);
    }

    @Override
    public void updateLoginUser(User user) {

    }
}
