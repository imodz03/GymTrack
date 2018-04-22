package com.elliotb.Resources.CRUDResources;

import com.elliotb.Auth.Annotations.AuthRequired;
import com.elliotb.Auth.Beans.ROLE;
import com.elliotb.DAO.PlanDAO;
import com.elliotb.DAO.PlannedWorkoutsDAO;
import com.elliotb.DAO.WorkoutDAO;
import com.elliotb.Entity.Plan;
import com.elliotb.Entity.Workout;
import com.elliotb.Helpers.EasyJSON;
import com.elliotb.Helpers.UUID;
import com.elliotb.Helpers.tokenDecrypter;
import com.elliotb.Services.PlanService;
import com.google.inject.Inject;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.util.List;

@Produces("Application/JSON")
@Consumes("Application/JSON")
@Path("/API/resource/plan")
public class PlanResource implements ICRUDResource<Plan> {

    @Inject
    private PlanDAO dao;

    @Inject
    PlannedWorkoutsDAO pwDAO;

    @Inject
    WorkoutDAO workoutDAO;

    @Inject
    PlanService planService;

    @Inject
    tokenDecrypter decrypter;

    @GET
    @AuthRequired(ROLE.MODERATOR)
    public Response getAll() {
        //doesn't return all associated workouts
        return Response.ok(dao.getAll()).build();
    }

    @GET
    @AuthRequired
    @Path("/mine")
    public Response getMine(@Context HttpHeaders httpHeaders){
        return Response.ok(dao.getMine(decrypter.getId(httpHeaders))).build();
    }

    @GET
    @Path("/{id}")
    public Response getByID(@PathParam("id") String id) {

        Plan resp = dao.getById(id);
        // TODO: 03/01/2018 add workout population
        return Response.ok().build();
    }

    @POST
    @AuthRequired
    public Response create(Plan plan, @Context HttpHeaders httpHeaders) {

        if (plan.getPlanID().isEmpty()){
            plan.setPlanID(UUID.getUUID());
        }

        int res = dao.create(plan, decrypter.getId(httpHeaders));

        System.out.println();

        if (res == 1){
            return Response.ok(EasyJSON.convert("id", plan.getPlanID())).build();
        }else{
            return Response.ok(res).build();
        }
    }

    @PUT
    @Path("/{id}")
    @AuthRequired
    public Response update(@PathParam("id") String id, Plan plan) {
        return Response.ok(dao.update(plan, plan.getUser().getUserID(), id)).build();
    }

    @DELETE
    @Path("{id}")
    @AuthRequired
    public Response delete(@PathParam("id") String id) {
        int res = planService.deletePlan(id);
        return Response.ok(res).build();
    }

    @GET
    @Path("/workouts/{id}")
    public Response getPlanWorkouts(@PathParam("id")String id){
        List<String> ids = pwDAO.getWorkoutIds(id);
        if(ids.size() > 0){
            List<Workout> workouts = workoutDAO.getFromIdList(ids);
            return Response.ok(workouts).build();
        }else{
            return Response.ok().build();
        }
    }
}
