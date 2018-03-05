package com.elliotb.Resources.CRUDResources;

import com.elliotb.DAO.PlanDAO;
import com.elliotb.Entity.Plan;
import com.elliotb.Helpers.UUID;
import com.google.inject.Inject;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Produces("Application/JSON")
@Consumes("Application/JSON")
@Path("/API/resource/plan")
public class PlanResource implements ICRUDResource<Plan> {

    @Inject
    private PlanDAO dao;

    @GET
    public Response getAll() {
        //doesn't return all associated workouts
        return Response.ok(dao.getAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response getByID(@PathParam("id") String id) {

        Plan resp = dao.getById(id);
        // TODO: 03/01/2018 add workout population
        return Response.ok().build();
    }

    @POST
    public Response create(Plan plan) {

        if (plan.getPlanID().isEmpty()){
            plan.setPlanID(UUID.getUUID());
        }

        int res = dao.create(plan, plan.getUser().getUserID());

        return Response.ok(res).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") String id, Plan plan) {
        return Response.ok(dao.update(plan, plan.getUser().getUserID(), id)).build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") String id) {
        return Response.ok(dao.delete(id)).build();
    }
}
