package Resources.CRUDResources;

import DAO.LogDAO;
import Entity.Log;
import Entity.Workout;
import Helpers.UUID;
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

        if (log.getLogID().isEmpty()){
            log.setLogID(UUID.getUUID());
        }

        int result = dao.create(log.getLogID(), log.getSetID(),
                log.getWorkout().getWorkoutID(), log.getUser().getUserID());

        return Response.ok(result).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") String id, Log log) {

        int result = dao.update(id, log.getSetID(), log.getWorkout().getWorkoutID(),
                log.getUser().getUserID());

        return Response.ok(result).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") String id) {

        int res = dao.delete(id);

        return Response.ok(res).build();
    }
}
