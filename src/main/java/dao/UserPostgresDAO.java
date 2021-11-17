package dao;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import model.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserPostgresDAO implements UserDAO {
    @Resource(lookup = "java:/PostgresDS")
    private DataSource ds;

    @Override
    public List<User> getAll() {
        var result = new ArrayList<User>();
        var sql = "SELECT id, login FROM users";

        try (var conn = ds.getConnection();
             var stmt = conn.prepareStatement(sql);
             var rs = stmt.executeQuery()) {

            while (rs.next()) {
                var id = rs.getObject("id",UUID.class);
                var login = rs.getString("login");
                var user = User.hydrate(id, login);
                result.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public User getByLogin(String login) {
        User result = null;
        var sql = "SELECT id FROM users WHERE login = ?";
        try (var conn = ds.getConnection();
             var stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, login);

            try(var rs = stmt.executeQuery()){
                while (rs.next()) {
                    var id = rs.getObject("id", UUID.class);
                    result = User.hydrate(id, login);
                }
           }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }

    @Override
    public void persist(User user){
        var sql = "INSERT INTO users(id, login) VALUES (?,?)";

        try(var conn = ds.getConnection();
        var stmt = conn.prepareStatement(sql)){
            stmt.setObject(1,user.getId());
            stmt.setString(2, user.getLogin());
            stmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(User user){
        var sql = "DELETE FROM users where id= ?";
        try(var conn = ds.getConnection();
        var stmt = conn.prepareStatement(sql)) {

            stmt.setObject(1, user.getId());
            stmt.executeUpdate();

        }catch (SQLException e) {
                e.printStackTrace();
        }
    }

    @Override
    public void updateLoginUser(User user){
        var sql = "UPDATE users SET login = ? WHERE id = ?";
        try(var conn = ds.getConnection();
            var stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getLogin());
            stmt.setObject(2, user.getId());
            stmt.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}