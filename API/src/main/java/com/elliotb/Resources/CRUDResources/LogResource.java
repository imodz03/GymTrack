package com.elliotb.Resources.CRUDResources;

import com.elliotb.Auth.Annotations.AuthRequired;
import com.elliotb.Auth.Beans.ROLE;
import com.elliotb.DAO.LogDAO;
import com.elliotb.Entity.Log;
import com.elliotb.Entity.Set;
import com.elliotb.Entity.Workout;
import com.elliotb.Helpers.UUID;
import com.elliotb.Helpers.tokenDecrypter;
import com.elliotb.Services.LogService;
import com.elliotb.Services.WorkoutService;
import com.google.inject.Inject;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Produces("Application/JSON")
@Consumes("Application/JSON")
@Path("/API/resource/log")
public class LogResource implements ICRUDResource<Log> {

    @Inject
    LogDAO dao;

    @Inject
    WorkoutService workoutService;

    @Inject
    LogService logService;

    @Inject
    tokenDecrypter td;

    @GET
    @AuthRequired(ROLE.MODERATOR)
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
    @AuthRequired
    public Response getByID(@PathParam("id") String id) {

        Log resp = dao.getById(id);

        resp.setWorkout(workoutService.populate(resp.getWorkout()));

        return Response.ok(resp).build();
    }

    @POST
    @AuthRequired
    public Response create(Log log, @Context HttpHeaders httpHeaders) {

        if (log.getLogID().isEmpty()){
            log.setLogID(UUID.getUUID());
        }

        int result = dao.create(log.getLogID(), log.getSetID(),
                log.getWorkout().getWorkoutID(), log.getUser().getUserID());

        return Response.ok(result).build();
    }

    @PUT
    @Path("/{id}")
    @AuthRequired
    public Response update(@PathParam("id") String id, Log log) {

        int result = dao.update(id, log.getSetID(), log.getWorkout().getWorkoutID(),
                log.getUser().getUserID());

        return Response.ok(result).build();
    }

    @DELETE
    @Path("/{id}")
    @AuthRequired
    public Response delete(@PathParam("id") String id) {

        int res = dao.delete(id);

        return Response.ok(res).build();
    }

    @POST
    @Path("/quick/{id}")
    @AuthRequired(ROLE.MEMBER)
    @Consumes(MediaType.TEXT_PLAIN) //nothing needs to be posted, nothing makes sense to post so a simple boolean is posted
    public Response quickLog(@PathParam("id")String id, String workoutID, @Context HttpHeaders httpHeaders){
        String userID = td.getId(httpHeaders);
        int res = logService.quickLog(id, workoutID, userID);
        return Response.ok(res).build();
    }

    @GET
    @Path("/workout/{id}")
    @AuthRequired
    public Response getForWorkout(@PathParam("id")String id){
        return Response.ok(dao.getByWorkoutID(id)).build();
    }

    @POST
    @Path("/create/{id}")
    public Response CreateLog(@PathParam("id")String workoutID, List<Set> sets, @Context HttpHeaders httpHeaders){

        String userID = td.getId(httpHeaders);

        int resp = logService.createLog(workoutID, sets, userID);

        return Response.ok(resp).build();
    }
}
