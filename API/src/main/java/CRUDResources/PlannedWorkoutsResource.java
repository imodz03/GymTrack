package CRUDResources;

import DAO.PlannedWorkoutsDAO;
import Entity.PlannedWorkouts;
import Helpers.UUID;
import Services.WorkoutService;
import com.google.inject.Inject;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Produces("Application/JSON")
@Consumes("Application/JSON")
@Path("/API/resource/pw")
public class PlannedWorkoutsResource implements ICRUDResource<PlannedWorkouts> {

    @Inject
    private PlannedWorkoutsDAO dao;

    @Inject
    private WorkoutService workoutService;

    @GET
    public Response getAll() {

        List<PlannedWorkouts> res = dao.getAll();

        if (res.size() > 0){
            for (PlannedWorkouts re : res) {
                re.setWorkout(workoutService.populate(re.getWorkout()));
            }
        }

        return Response.ok(res).build();
    }

    @GET
    @Path("/{id}")
    public Response getByID(@PathParam("id") String id) {

        PlannedWorkouts res = dao.getById(id);

        if (!res.getPwID().isEmpty()){
            res.setWorkout(workoutService.populate(res.getWorkout()));
        }

        return Response.ok(res).build();
    }

    @POST
    public Response create(PlannedWorkouts plannedWorkouts) {

        if (plannedWorkouts.getPwID().isEmpty()){
            plannedWorkouts.setPwID(UUID.getUUID());
        }

        int resp = dao.create(plannedWorkouts, plannedWorkouts.getWorkout().getWorkoutID());

        return Response.ok(1).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") String id, PlannedWorkouts plannedWorkouts) {

        System.out.println(plannedWorkouts);

        int res = dao.update(plannedWorkouts, plannedWorkouts.getWorkout().getWorkoutID(), id);

        return Response.ok(res).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") String id) {
        return Response.ok(dao.delete(id)).build();
    }
}
