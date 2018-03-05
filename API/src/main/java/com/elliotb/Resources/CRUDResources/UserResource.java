package com.elliotb.Resources.CRUDResources;

import com.elliotb.Auth.Annotations.AuthRequired;
import com.elliotb.Auth.Beans.AuthUser;
import com.elliotb.Auth.Beans.ROLE;
import com.elliotb.DAO.UserDAO;
import com.elliotb.Entity.User;
import com.elliotb.Helpers.UUID;
import com.elliotb.Helpers.tokenDecrypter;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
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

    @Inject
    private Algorithm algorithm;

    public UserResource(){}

    @GET
    public Response getAll(){
        return Response.ok(dao.getAll()).build();
    }



    @POST
    @AuthRequired(ROLE.ADMIN)
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

    @Path("/register")
    @POST
    public String register(User user){

        String available = dao.getUsernames(user.getUsername());

        System.out.println(available);

        if (available != null){
            return "{\"status\": \"NA\"}";
        }else{

            String uuid = UUID.getUUID();
            user.setUserID(uuid);
            int res = dao.createUser(user);
            if(res == 1){
                AuthUser AU = new AuthUser(user.getUsername(), uuid);
                AU.setRole(ROLE.ADMIN); // TODO: 23/02/2018 set roles in db

                String token = JWT.create().withIssuer("ElliotB")
                        .withClaim("username", AU.getUsername())
                        .withClaim("userID", AU.getUserID())
                        .withClaim("role", AU.getRole().val)
                        .sign(algorithm);

                return "{\"status\": \"" + token + "\"}";
            }else{
                return "{\"status\": \"error\"}";
            }


        }
    }
}
