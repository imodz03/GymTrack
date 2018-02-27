package Resources.CRUDResources;

import Auth.Annotations.AuthRequired;
import Auth.Beans.ROLE;
import DAO.UserDAO;
import Entity.User;
import Helpers.UUID;
import Helpers.tokenDecrypter;
import com.google.inject.Inject;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

@Produces("Application/JSON")
@Consumes("Application/JSON")
@Path("/API/resource/user")
public class UserResource implements ICRUDResource<User> {

    // TODO: 01/12/2017 add correct response codes 
    
    @Inject
    private UserDAO dao;

    @Inject
    private tokenDecrypter decrypter;

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

    @Path("/{id}")
    @PUT
    public Response update(@PathParam("id") String id, User user) {

        System.out.println(user.getWeight());

        int  update = dao.update(user);

        return Response.ok(update).build();
    }

    @Path("/{id}")
    @DELETE
    public Response delete(@PathParam("id") String id) {
        int delete = dao.delete(id);
        return Response.ok(delete).build();
    }

    @Path("/mine")
    @GET
    @AuthRequired(ROLE.MEMBER)
    public Response mine(@Context HttpHeaders httpHeaders){
        User u = dao.getById(decrypter.getId(httpHeaders));

        return Response.ok(u).build();
    }

    @Path("/mine/pref")
    @GET
    @AuthRequired(ROLE.MEMBER)
    public Response myPrefs(@Context HttpHeaders httpHeaders){
        String s= dao.getPrefs(decrypter.getId(httpHeaders));

        return Response.ok(s).build();
    }
}
