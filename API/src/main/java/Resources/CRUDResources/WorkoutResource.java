package Resources.CRUDResources;

import Auth.Annotations.AuthRequired;
import Auth.Beans.AuthUser;
import Auth.Beans.ROLE;
import DAO.WorkoutDAO;
import Entity.ExerciseList;
import Entity.Workout;
import Helpers.UUID;
import Helpers.tokenDecrypter;
import Services.WorkoutService;
import com.google.inject.Inject;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.util.List;

@Produces("Application/JSON")
@Consumes("Application/JSON")
@Path("/API/resource/workout")
public class WorkoutResource implements ICRUDResource<Workout> {

    @Inject
    private WorkoutDAO dao;

    @Inject
    private WorkoutService service;

    @Inject
    private tokenDecrypter tokenDecrypter;


    @GET
    public Response getAll() {
        List<Workout> get = dao.getAll();

        for (Workout workout : get) {
            service.populateExercise(workout);
        }

        return Response.ok(get).build();
    }

    @GET
    @Path("/mine")
    @AuthRequired(ROLE.MEMBER)
    public Response getMine(@Context HttpHeaders httpHeaders) {
        List<Workout> get = dao.getMine(tokenDecrypter.getId(httpHeaders));

        for (Workout workout : get) {
            service.populateExercise(workout);
        }

        return Response.ok(get).build();
    }

    @GET
    @Path("/{id}")
    public Response getByID(@PathParam("id") String id) {
        Workout w = dao.getByID(id);
        service.populateExercise(w);
        return Response.ok(w).build();
    }

    @POST
    @AuthRequired(ROLE.MEMBER)
    public Response create(Workout workout) {

        if (workout.getWorkoutID().isEmpty()){
            workout.setWorkoutID(UUID.getUUID());
        }

        ExerciseList el = new ExerciseList(UUID.getUUID());
        workout.setExerciseList(el);

        int result = dao.create(workout, workout.getUser().getUserID(), workout.getExerciseList().getELID());

        return Response.ok(result).build();
    }

    @PUT
    @Path("/{id}")
    @AuthRequired(ROLE.MEMBER)
    public Response update(@PathParam("id") String id, Workout workout) {

        int res = dao.update(workout, workout.getUser().getUserID(), workout.getExerciseList().getELID(), id);

        return Response.ok(res).build();
    }

    @DELETE
    @Path("/{id}")
    @AuthRequired(ROLE.MEMBER)
    public Response delete(@PathParam("id") String id) {
        return Response.ok(dao.delete(id)).build();
    }
}
