package com.elliotb.Resources.CRUDResources;

import com.elliotb.DAO.SetDAO;
import com.elliotb.Entity.Exercise;
import com.elliotb.Entity.Set;
import com.elliotb.Helpers.UUID;
import com.elliotb.Services.ExerciseService;
import com.elliotb.Services.SetService;
import com.google.inject.Inject;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.util.List;

@Produces("Application/JSON")
@Consumes("Application/JSON")
@Path("/API/resource/set")
public class SetResource implements ICRUDResource<Set> {

    @Inject
    SetDAO dao;

    @Inject
    ExerciseService exerciseService;

    @Inject
    SetService setService;

    @Override
    @GET
    public Response getAll() {
        List<Set> dbres = dao.getAll();

        if (dbres.size() > 0){
            for (Set dbre : dbres) {
                Exercise populated = exerciseService.populateExercise(dbre.getExercise());
                dbre.setExercise(populated);
            }
        }

        return Response.ok(dbres).build();
    }

    @Override
    @GET
    @Path("/{id}")
    public Response getByID(@PathParam("id")String id) {

        List<Set> dbres = dao.getById(id);

        if (dbres.size() > 0){
            for (Set dbre : dbres) {
                Exercise populated = exerciseService.populateExercise(dbre.getExercise());
                dbre.setExercise(populated);
            }
        }

        return Response.ok(dbres).build();
    }

    @GET
    @Path("/{id}/{exID}")
    public Response getByIDandEx(@PathParam("id")String id, @PathParam("exID")String exid) {

        List<Set> dbres = dao.getByIdAndEx(id, exid);

        if (dbres.size() > 0){
            for (Set dbre : dbres) {
                Exercise populated = exerciseService.populateExercise(dbre.getExercise());
                dbre.setExercise(populated);
            }
        }

        return Response.ok(dbres).build();
    }

    // TODO: 24/12/2017 proper response codes
    @POST
    public Response create(Set set, @Context HttpHeaders httpHeaders) {

        set.setSUID(UUID.getUUID());

        int res = dao.create(set, set.getExercise().getExerciseID(), set.getSUID());

        return Response.ok(res).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id")String id, Set set) {
        int res = dao.update(id, set.getExercise().getExerciseID(), set);
        return Response.ok(res).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id")String id) {
        int res = setService.deleteSet(id);
        return Response.ok(res).build();
    }

    @POST
    @Path("/{id}")
    public Response addSet(@PathParam("id")String id, Set set){
        set.setSUID(UUID.getUUID());
        int res = dao.create(set, set.getExercise().getExerciseID(), set.getSUID());
        return Response.ok(res).build();
    }


}
