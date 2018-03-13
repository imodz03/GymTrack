package com.elliotb.Resources.CRUDResources;

import com.elliotb.DAO.PlannedWorkoutsDAO;
import com.elliotb.Entity.PlannedWorkouts;
import com.elliotb.Helpers.UUID;
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
@Path("/API/resource/pw")
public class PlannedWorkoutsResource implements ICRUDResource<PlannedWorkouts> {

    @Inject
    private PlannedWorkoutsDAO dao;

    @Inject
    private WorkoutService workoutService;

    @Inject
    private PlanService planService;

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
    @Path("/pid/{id}")
    public Response getPlan(@PathParam("id")String id) {

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
    public Response create(PlannedWorkouts plannedWorkouts, @Context HttpHeaders httpHeaders) {

        if (plannedWorkouts.getPwID().isEmpty()){
            plannedWorkouts.setPwID(UUID.getUUID());
        }

        int resp = dao.create(plannedWorkouts, plannedWorkouts.getWorkout().getWorkoutID());

        return Response.ok(resp).build();
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

    @POST
    @Path("/plan")
    public Response addWorkouts(List<PlannedWorkouts> workouts){

        int res = planService.addWorkouts(workouts, workouts.get(0).getPlanID());
        return Response.ok(res).build();
    }

}
