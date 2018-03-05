package com.elliotb.Resources;

import com.elliotb.Auth.Beans.AuthUser;
import com.elliotb.Auth.Beans.ROLE;
import com.elliotb.DAO.AuthDAO;
import com.elliotb.Helpers.tokenVerifier;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/auth")
public class UserAuth {

    @Inject
    private Algorithm algorithm;

    @Inject
    private tokenVerifier tf;

    @Inject
    private AuthDAO dao;

    @POST
    @Path("/login")
    public String login(AuthUser au){

        String userID = dao.login(au.getUsername(), au.getPass());
        String token;


        if (userID != null){

            AuthUser AU = new AuthUser(au.getUsername(), userID);
            AU.setRole(ROLE.ADMIN); // TODO: 23/02/2018 set roles in db 

            token = JWT.create().withIssuer("ElliotB")
                    .withClaim("username", AU.getUsername())
                    .withClaim("userID", AU.getUserID())
                    .withClaim("role", AU.getRole().val)
                    .sign(algorithm);

        }else{

            token = "invalid";

        }

        return "{\"token\": \"" + token + "\"}";

    }

    @POST
    @Path("/verify")
    public boolean valid(AuthUser au){
        return tf.verify(au);
    }

}
