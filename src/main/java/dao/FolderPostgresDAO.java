package dao;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import model.Folder;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class FolderPostgresDAO implements FolderDAO{
    @Resource(lookup = "java:/PostgresDS")
    private DataSource ds;

    @Override
    public List<Folder> getAll() {
        var result = new ArrayList<Folder>();
        var sql = "SELECT id, name FROM folders";

        try (var conn = ds.getConnection();
             var stmt = conn.prepareStatement(sql);
             var rs = stmt.executeQuery()){

            while(rs.next()){
                var id = rs.getObject("id", UUID.class);
                var name = rs.getString("name");
                var folder = Folder.hydrate(id, name);
                result.add(folder);
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void persist(Folder folder) {
        var sql = "INSERT INTO folders(id, name) VALUES (?,?)";

        try(var conn = ds.getConnection();
        var stmt = conn.prepareStatement(sql)){
            stmt.setObject(1, folder.getId());
            stmt.setString(2, folder.getName());
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}
