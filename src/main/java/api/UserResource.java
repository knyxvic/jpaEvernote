package api;

import controllers.ExistentLoginException;
import controllers.UserController;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.PostUpdate;
import jakarta.ws.rs.*;
import controllers.UserControllerImpl;
import jakarta.ws.rs.core.MediaType;
import model.User;


import javax.print.attribute.standard.Media;
import java.util.List;

@ApplicationScoped
@Path("/users")
public class UserResource {
    @Inject
    private UserControllerImpl userController;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAllUsers(){
        return this.userController.getAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User createUser(UserJsonInput json){
        return userController.createUser(json.login);
    }

    @GET
    @Path("/{login}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(@PathParam("login") String login) {

        return userController
                .getByLogin(login)
                //Return result if isPresent, else return NotFoundException
                .orElseThrow(NotFoundException::new);
    }

    @Path("/{login}")
    @DELETE
    public void deleteUser(@PathParam("login")String login){
        var user = userController
                .getByLogin(login)
                .orElseThrow(NotFoundException::new);
        userController
                .deleteUser(user);
    }

    /*@Path("/{login}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @PUT
    public User updateLoginUser(@PathParam("login") String login, UserJsonInput json) {
        var user = userController
                .getByLogin(login)
                .orElseThrow(NotFoundException::new);

        try {
            return userController.updateLoginUser(user, json.login);
        } catch (ExistentLoginException e) {
            throw new BadRequestException();
        }
    }*/
}