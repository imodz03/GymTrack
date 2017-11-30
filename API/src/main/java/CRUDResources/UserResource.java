package CRUDResources;

import DAO.UserDAO;
import Entity.User;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Produces("Application/JSON")
@Consumes("Application/JSON")
@Path("/API/resource/user")
public class UserResource {

    private UserDAO dao;

    public UserResource(UserDAO dao){
        this.dao = dao;
    }

    @GET
    public Response getAll(){

        return Response.ok(dao.getAll()).build();

    }

    @POST
    public Response create(User user){

        return Response.ok(dao.createUser(user)).build();

    }

}
