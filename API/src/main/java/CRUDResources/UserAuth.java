package CRUDResources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/user")
public class UserAuth {

    @GET
    @Path("/login")
    public String login(){

        return "dummy";

    }

}
