package api;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import controllers.FolderControllerImpl;
import jakarta.ws.rs.core.MediaType;
import model.Folder;


import java.util.List;

@ApplicationScoped
@Path("/folders")
public class FolderResource {
    @Inject
    private FolderControllerImpl folderController;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Folder> getAllFolders(){return this.folderController.getAll();}

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Folder createFolder(FolderJsonInput json){return folderController.createFolder(json.name);}
}
