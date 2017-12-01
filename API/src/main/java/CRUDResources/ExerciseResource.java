package CRUDResources;

import DAO.ExerciseDAO;
import Entity.Exercise;
import Helpers.UUID;
import com.google.inject.Inject;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Produces("Application/JSON")
@Consumes("Application/JSON")
@Path("/API/resource/exercise")
public class ExerciseResource implements ICRUDResource<Exercise> {

    @Inject
    private ExerciseDAO dao;

    public ExerciseResource(){
        //this.dao = dao;
    }

    @GET
    public Response getAll(){
        return Response.ok(dao.getAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response getByID(@PathParam("id")String id){
        return Response.ok(dao.getById(id)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id")String id){
        Number result = dao.delete(id);
        if ((int)result == 1){
            return Response.ok().build();
        }else {
            return Response.status(400).build();
        }
    }

    @POST
    public Response create(Exercise exercise){

        if (exercise.getExerciseID().isEmpty()){
            exercise.setExerciseID(UUID.getUUID());
        }

        int check = dao.create(exercise);
        return Response.ok(check).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id")String id, Exercise body){
        System.out.println(body);

        int check = dao.update(body, id);

        return Response.ok(check).build();

    }



}
