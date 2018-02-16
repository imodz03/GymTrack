package Resources.CRUDResources;

import DAO.WorkoutDAO;
import Entity.Workout;
import Helpers.UUID;
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

    @GET
    @Path("/{id}")
    public Response getByID(@PathParam("id") String id) {
        return Response.ok(dao.getByID(id)).build();
    }

    @POST
    public Response create(Workout workout) {

        if (workout.getWorkoutID().isEmpty()){
            workout.setWorkoutID(UUID.getUUID());
        }

        int result = dao.create(workout, workout.getUser().getUserID(), workout.getExerciseList().getELID());

        return Response.ok(result).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") String id, Workout workout) {

        int res = dao.update(workout, workout.getUser().getUserID(), workout.getExerciseList().getELID(), id);

        return Response.ok(res).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") String id) {
        return Response.ok(dao.delete(id)).build();
    }
}
