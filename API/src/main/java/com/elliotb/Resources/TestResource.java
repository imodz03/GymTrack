package com.elliotb.Resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/test")
public class TestResource {

    @GET
    public boolean test(){
        return true;
    }

}
