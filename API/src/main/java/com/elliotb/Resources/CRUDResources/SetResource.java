package com.elliotb.Resources.CRUDResources;

import com.elliotb.Auth.Annotations.AuthRequired;
import com.elliotb.Auth.Beans.ROLE;
import com.elliotb.DAO.SetDAO;
import com.elliotb.Entity.Exercise;
import com.elliotb.Entity.Set;
import com.elliotb.Helpers.EasyJSON;
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
    @AuthRequired(ROLE.ADMIN)
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
    @AuthRequired
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
    @AuthRequired
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
    @AuthRequired
    public Response create(Set set, @Context HttpHeaders httpHeaders) {

        set.setSUID(UUID.getUUID());

        int res = dao.create(set, set.getExercise().getExerciseID(), set.getSUID());

        return Response.ok(res).build();
    }

    @PUT
    @Path("/{id}")
    @AuthRequired
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
    @AuthRequired
    public Response addSet(@PathParam("id")String id, Set set){
        set.setSUID(UUID.getUUID());
        int res = dao.create(set, set.getExercise().getExerciseID(), set.getSUID());
        return Response.ok(res).build();
    }

    @POST
    @Path("/list")
    @AuthRequired
    public Response createList(List<Set> sets){
        System.out.println(sets);

        String setId = UUID.getUUID();

        int res = 0;

        for (Set set : sets) {

            set.setSetID(setId);
            String suid = UUID.getUUID();
            set.setSUID(suid);

            res = dao.create(set, set.getExercise().getExerciseID(), suid);

        }

        if (res == 1){
            return Response.ok(EasyJSON.convert("setID", setId)).build();
        }else{
            return Response.ok().build();
        }
    }


}
