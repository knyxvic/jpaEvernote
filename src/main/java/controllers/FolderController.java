package controllers;

import model.Folder;

import java.util.List;

public interface FolderController {
    Folder createFolder(String name);
    List<model.Folder> getAll();
}
