package CRUDResources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;


@Produces("Application/JSON")
@Consumes("Application/JSON")
@Path("/API/resource/goal")

public class GoalResource implements ICRUDResource<GoalResource>{

    @Override
    @GET
    public Response getAll() {
        return null;
    }

    @Override
    public Response getByID(String id) {
        return null;
    }

    @Override
    public Response create(GoalResource goalResource) {
        return null;
    }

    @Override
    public Response update(String id, GoalResource goalResource) {
        return null;
    }

    @Override
    public Response delete(String id) {
        return null;
    }
}
