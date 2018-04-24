package com.elliotb.Resources;

import com.elliotb.Auth.Beans.AuthUser;
import com.elliotb.Auth.Beans.ROLE;
import com.elliotb.DAO.AuthDAO;
import com.elliotb.DAO.UserDAO;
import com.elliotb.Helpers.PasswordHash;
import com.elliotb.Helpers.tokenVerifier;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.Scanner;

@Path("/auth")
public class UserAuth {

    @Inject
    private Algorithm algorithm;

    @Inject
    private tokenVerifier tf;

    @Inject
    private AuthDAO dao;

    @Inject
    private UserDAO userDAO;

    @POST
    @Path("/login")
    public String login(AuthUser au){

        String salt = userDAO.getSalt(au.getUsername());
        String token = "invalid";

        if (salt != null){
            byte[] password = PasswordHash.hashPassword(au.getPass().toCharArray(), salt.getBytes());

            String userID = dao.login(au.getUsername(), new String(password));

            if (userID != null){

                AuthUser AU = new AuthUser(au.getUsername(), userID);
                AU.setRole(ROLE.MEMBER); // TODO: 23/02/2018 set roles in db

                token = JWT.create().withIssuer("ElliotB")
                        .withClaim("username", AU.getUsername())
                        .withClaim("userID", AU.getUserID())
                        .withClaim("role", AU.getRole().val)
                        .sign(algorithm);

            }else{

                token = "invalid";

            }

        }


        return "{\"token\": \"" + token + "\"}";

    }

    @POST
    @Path("/verify")
    public boolean valid(AuthUser au){
        return tf.verify(au);
    }

    @POST
    @Path("/test")
    public Response testing(@Context ContainerRequestContext crc) {
        Scanner s = new Scanner(crc.getEntityStream()).useDelimiter("\\A");

        Collection<String> testing = crc.getPropertyNames();

        String result = s.hasNext() ? s.next() : "";
        System.out.println(result);
        return Response.ok().build();
    }

}
