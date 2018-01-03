package CRUDResources;

import DAO.PlannedWorkoutsDAO;
import Entity.PlannedWorkouts;
import com.google.inject.Inject;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Produces("Application/JSON")
@Consumes("Application/JSON")
@Path("/API/resource/pw")
public class PlannedWorkoutsResource implements ICRUDResource<PlannedWorkouts> {

    @Inject
    private PlannedWorkoutsDAO dao;

    @GET
    public Response getAll() {
        return null;
    }

    @Override
    public Response getByID(String id) {
        return null;
    }

    @Override
    public Response create(PlannedWorkouts plannedWorkouts) {
        return null;
    }

    @Override
    public Response update(String id, PlannedWorkouts plannedWorkouts) {
        return null;
    }

    @Override
    public Response delete(String id) {
        return null;
    }
}
