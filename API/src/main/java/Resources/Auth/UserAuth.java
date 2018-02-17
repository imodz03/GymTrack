package Resources.Auth;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/auth")
public class UserAuth {

    @GET
    @Path("/login")
    public String login(){

        return "dummy";

    }

}
