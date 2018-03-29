package com.elliotb.Resources.CRUDResources;

import com.elliotb.Auth.Annotations.AuthRequired;
import com.elliotb.Auth.Beans.ROLE;
import com.elliotb.DAO.GoalDAO;
import com.elliotb.Entity.Goal;
import com.elliotb.Entity.Set;
import com.elliotb.Helpers.UUID;
import com.elliotb.Helpers.tokenDecrypter;
import com.elliotb.Services.SetService;
import com.google.inject.Inject;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.util.List;


@Produces("Application/JSON")
@Consumes("Application/JSON")
@Path("/API/resource/goal")
public class GoalResource implements ICRUDResource<Goal>{

    @Inject
    private GoalDAO dao;

    @Inject
    private tokenDecrypter td;

    @Inject
    private SetService setService;

    @GET
    @AuthRequired
    public Response getAll() {
        return Response.ok(dao.getAll()).build();
    }

    @GET
    @Path("/mine")
    @AuthRequired
    public Response getMine(@Context HttpHeaders httpHeaders){
        String userID = td.getId(httpHeaders);
        List<Goal> mine = dao.getMine(userID);
        return Response.ok(mine).build();
    }

    @GET
    @Path("/{id}")
    @AuthRequired
    public Response getByID(@PathParam("id")String id) {
        return Response.ok(dao.getById(id)).build();
    }

    @POST
    @AuthRequired
    public Response create(Goal goal, @Context HttpHeaders httpHeaders) {
        if (goal.getGoalID().isEmpty()){
            goal.setGoalID(UUID.getUUID());
        }

        System.out.println(goal);

        return Response.ok(
                dao.create(goal.getGoalID(), goal, goal.getSet().get(0).getSetID(), goal.getUser().getUserID())
        ).build();
    }

    @PUT
    @Path("/{id}")
    @AuthRequired
    public Response update(@PathParam("id")String id, Goal goal) {
        return Response.ok(dao.update(id, goal.getSet().get(0).getSetID(), goal)).build();
    }

    @DELETE
    @Path("/{id}")
    @AuthRequired(ROLE.MODERATOR)
    public Response delete(@PathParam("id") String id) {
        return Response.ok(dao.delete(id)).build();
    }
}
