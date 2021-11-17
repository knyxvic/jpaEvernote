package dao;
import model.Folder;

import java.util.List;

public interface FolderDAO {
    List<Folder> getAll();
    void persist(Folder folder);
}
