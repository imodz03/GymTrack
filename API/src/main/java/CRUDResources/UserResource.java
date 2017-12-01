package CRUDResources;

import DAO.UserDAO;
import Entity.User;
import Helpers.UUID;
import com.google.inject.Inject;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Produces("Application/JSON")
@Consumes("Application/JSON")
@Path("/API/resource/user")
public class UserResource implements ICRUDResource<User> {

    // TODO: 01/12/2017 add correct response codes 
    
    @Inject
    private UserDAO dao;

    public UserResource(){}

    @GET
    public Response getAll(){
        return Response.ok(dao.getAll()).build();
    }



    @POST
    public Response create(User user){

        if (user.getUserID().isEmpty()){
            user.setUserID(UUID.getUUID());
        }

        System.out.println(user);

        int resp = dao.createUser(user);

        return Response.ok(resp).build();

    }

    @Path("/{id}")
    @GET
    public Response getByID(@PathParam("id")String id) {
        return Response.ok(dao.getById(id)).build();
    }

    @Override
    public Response update(String id, User user) {
        return null;
    }

    @Override
    public Response delete(String id) {
        return null;
    }
}
