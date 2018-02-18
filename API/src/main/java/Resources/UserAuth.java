package Resources;

import Auth.Beans.AuthUser;
import Auth.Beans.ROLE;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/auth")
public class UserAuth {

    @Inject
    private Algorithm algorithm;

    @GET
    @Path("/login")
    public String login(){

        AuthUser AU = new AuthUser("Brown27", "8e4a0e98-9089-420d-823b-662e878b850b");
        AU.setRole(ROLE.ADMIN);

        String token = JWT.create().withIssuer("ElliotB")
                .withClaim("username", AU.getUsername())
                .withClaim("userID", AU.getUserID())
                .withClaim("role", AU.getRole().val)
                .sign(algorithm);

        return token;

    }

}
