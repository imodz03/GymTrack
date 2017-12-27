package CRUDResources;

import DAO.WorkoutDAO;
import Entity.Workout;
import com.google.inject.Inject;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Produces("Application/JSON")
@Consumes("Application/JSON")
@Path("/API/resource/workout")
public class WorkoutResource implements ICRUDResource<Workout> {

    @Inject
    private WorkoutDAO dao;

    @GET
    public Response getAll() {
        return Response.ok(dao.getAll()).build();
    }

    @Override
    public Response getByID(String id) {
        return null;
    }

    @POST
    public Response create(Workout workout) {
        return null;
    }

    @Override
    public Response update(String id, Workout workout) {
        return null;
    }

    @Override
    public Response delete(String id) {
        return null;
    }
}
