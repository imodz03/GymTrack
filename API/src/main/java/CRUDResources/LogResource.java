package CRUDResources;

import DAO.LogDAO;
import Entity.Log;
import Entity.Workout;
import Services.WorkoutService;
import com.google.inject.Inject;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Produces("Application/JSON")
@Consumes("Application/JSON")
@Path("/API/resource/Log")
public class LogResource implements ICRUDResource<Log> {

    @Inject
    LogDAO dao;

    @Inject
    WorkoutService workoutService;

    @GET
    public Response getAll() {

        List<Log> resp = dao.getAll();

        for (Log log : resp) {

            Workout temp = workoutService.populate(log.getWorkout());
            log.setWorkout(temp);

        }

        return Response.ok(resp).build();
    }

    @GET
    @Path("/{id}")
    public Response getByID(@PathParam("id") String id) {

        Log resp = dao.getById(id);

        resp.setWorkout(workoutService.populate(resp.getWorkout()));

        return Response.ok(resp).build();
    }

    @POST
    public Response create(Log log) {
        return null;
    }

    @Override
    public Response update(String id, Log log) {
        return null;
    }

    @Override
    public Response delete(String id) {
        return null;
    }
}
