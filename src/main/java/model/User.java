package model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name= "users")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private UUID id;
    @Column(name = "login")
    private String login;

    public User(String login){
        this.login = login;
    }

    protected User(){}

    private User(UUID id, String login){
        this.id = id;
        this.login = login;
    }


    public static User hydrate(UUID id, String login){
        return new User(id, login);
    }


    public UUID getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }
    public void setLogin(String newlogin){
        this.login = newlogin;
    }
}
