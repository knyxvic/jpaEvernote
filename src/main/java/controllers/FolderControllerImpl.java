package controllers;

import dao.FolderDAO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import model.Folder;

import java.util.List;

@ApplicationScoped
public class FolderControllerImpl implements FolderController{
    @Inject
    private FolderDAO folderDAO;

    @Override
    @Transactional
    public Folder createFolder(String name) {
        var folder = new Folder(name);
        folderDAO.persist(folder);
        return folder;
    }

    @Override
    public List<Folder> getAll() {
        return folderDAO.getAll();
    }
}
