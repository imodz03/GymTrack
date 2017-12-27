package CRUDResources;

import DAO.WorkoutDAO;
import Entity.Workout;
import com.google.inject.Inject;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Produces("Application/JSON")
@Consumes("Application/JSON")
@Path("/API/resource/Workout")
public class WorkoutResource implements ICRUDResource<Workout> {

    @Inject
    private WorkoutDAO dao;

    @GET
    public Response getAll() {
        return null;
    }

    @Override
    public Response getByID(String id) {
        return null;
    }

    @Override
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
