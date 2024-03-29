package com.elliotb.Resources.CRUDResources;

import com.elliotb.DAO.ExerciseListDAO;
import com.elliotb.Entity.Exercise;
import com.elliotb.Entity.ExerciseList;
import com.elliotb.Helpers.OneToManyCombiner;
import com.elliotb.Auth.Annotations.AuthRequired;
import com.elliotb.Auth.Beans.ROLE;
import com.elliotb.Services.ExerciseListService;
import com.elliotb.Services.ExerciseService;
import com.google.inject.Inject;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Produces("Application/JSON")
@Consumes("Application/JSON")
@Path("/API/resource/el")
public class ExerciseListResource {

    @Inject
    private ExerciseListDAO dao;

    @Inject
    private ExerciseService exerciseService;

    @Inject
    private ExerciseListService exerciseListService;

    @GET
    @AuthRequired(ROLE.MODERATOR)
    public Response getAll() {

        List<ExerciseList> list = dao.getAll();

        if (list.size() > 0){
            list = OneToManyCombiner.combineExercises(list);
            for (ExerciseList exerciseList : list) {
                exerciseService.populateExercises(exerciseList.getExercises());
            }
        }

        return Response.ok(list).build();
    }

    @Path("/{id}")
    @GET
    @AuthRequired(ROLE.MEMBER)
    public Response getByID(@PathParam("id") String id) {

        List<ExerciseList> list = dao.getById(id);

        if (list.size() > 0){
            list = OneToManyCombiner.combineExercises(list);
            for (ExerciseList exerciseList : list) {
                exerciseService.populateExercises(exerciseList.getExercises());
            }
        }

        return Response.ok(list.get(0)).build();

    }

    @POST
    @AuthRequired
    public Response create(ExerciseList exerciseList) {
        boolean result = exerciseListService.createELEntry(exerciseList);
        return Response.ok(result).build();
    }

    //not sure how applicable this is unless adding
    public Response update(String id, ExerciseList exerciseList) {
        return null;
    }

    @DELETE
    @Path("/{id}")
    @AuthRequired(ROLE.MODERATOR)
    public Response delete(@PathParam("id")String id) {
        int result = dao.delete(id);
        return Response.ok(result).build();
    }

    @DELETE
    @Path("/{id}/{del}")
    @AuthRequired
    public Response deleteExercise(@PathParam("id") String id, @PathParam("del") String exerciseID){
        int result = dao.deleteExercise(id, exerciseID);
        return Response.ok(result).build();
    }

    @POST
    @Path("/{id}/add")
    @AuthRequired
    public Response addExercise(@PathParam("id")String id, Exercise exercise){
        int res = dao.add(id, exercise.getExerciseID());
        return Response.ok(res).build();
    }
}
