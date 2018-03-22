package com.elliotb.Resources.CRUDResources;

import com.elliotb.Auth.Annotations.AuthRequired;
import com.elliotb.Auth.Beans.ROLE;
import com.elliotb.DAO.ExerciseListDAO;
import com.elliotb.DAO.WorkoutDAO;
import com.elliotb.Entity.Exercise;
import com.elliotb.Entity.ExerciseList;
import com.elliotb.Entity.User;
import com.elliotb.Entity.Workout;
import com.elliotb.Helpers.EasyJSON;
import com.elliotb.Helpers.UUID;
import com.elliotb.Helpers.tokenDecrypter;
import com.elliotb.Services.PlanService;
import com.elliotb.Services.WorkoutService;
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
    private ExerciseListDAO elDAO;

    @Inject
    private WorkoutService service;

    @Inject
    private PlanService planService;

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
            if (workout != null){
                service.populateExercise(workout);
            }
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
    public Response create(Workout workout, @Context HttpHeaders httpHeaders) {

        if (workout.getWorkoutID().isEmpty()){
            workout.setWorkoutID(UUID.getUUID());
        }

        String uuid = UUID.getUUID();
        workout.getExerciseList().setELID(uuid);

        String uuid2 = UUID.getUUID();

        workout.setUser(new User(tokenDecrypter.getId(httpHeaders)));

        int result = dao.create(workout, workout.getUser().getUserID(), workout.getExerciseList().getELID(), uuid2);

        if (result == 1){
            for (Exercise exercise : workout.getExerciseList().getExercises()) {
                result = elDAO.create(uuid, exercise.getExerciseID());
            }
        }

        if(result == 1){
            return Response.ok(EasyJSON.convert("resp", workout.getWorkoutID())).build();
        }else{
            return Response.ok(result).build();
        }
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
        int res = planService.deleteWorkout(id);
        return Response.ok(res).build();
    }
}
