package com.elliotb.Resources.CRUDResources;

import com.elliotb.Auth.Annotations.AuthRequired;
import com.elliotb.Auth.Beans.ROLE;
import com.elliotb.DAO.ExerciseDAO;
import com.elliotb.Entity.Exercise;
import com.elliotb.Helpers.EasyJSON;
import com.elliotb.Helpers.UUID;
import com.google.inject.Inject;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

@Produces("Application/JSON")
@Consumes("Application/JSON")
@Path("/API/resource/exercise")
public class ExerciseResource implements ICRUDResource<Exercise> {

    @Inject
    private ExerciseDAO dao;

    public ExerciseResource(){
    }

    @GET
    public Response getAll(){
        return Response.ok(dao.getAll()).build();
    }

    @GET
    @Path("/{id}")
    @AuthRequired(ROLE.MEMBER)
    public Response getByID(@PathParam("id")String id){
        return Response.ok(dao.getById(id)).build();
    }

    @DELETE
    @Path("/{id}")
    @AuthRequired(ROLE.MODERATOR)
    public Response delete(@PathParam("id")String id){
        int result = dao.delete(id);
        if (result == 1){
            return Response.ok().build();
        }else {
            return Response.status(400).build();
        }
    }

    @POST
    @AuthRequired(ROLE.MEMBER)
    public Response create(Exercise exercise, @Context HttpHeaders httpHeaders){

        if (exercise.getExerciseID().isEmpty()){
            exercise.setExerciseID(UUID.getUUID());
        }

        int check = dao.create(exercise);

        if (check == 1){
            return Response.ok(EasyJSON.convert("resp", exercise.getExerciseID())).build();
        }else{
            return Response.ok(check).build();
        }
    }

    @PUT
    @Path("/{id}")
    @AuthRequired(ROLE.MODERATOR)
    public Response update(@PathParam("id")String id, Exercise body){
        System.out.println(body);

        int check = dao.update(body, id);

        return Response.ok(check).build();

    }



}
