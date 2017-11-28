package CRUDResources;

import DAO.ExerciseDAO;
import Entity.Exercise;
import Helpers.UUID;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Produces("Application/JSON")
@Consumes("Application/JSON")
@Path("/API/resource/exercise")
public class ExerciseResource{

    private ExerciseDAO dao;

    public ExerciseResource(ExerciseDAO dao){
        this.dao = dao;
    }

    @GET
    public List<Exercise> getAll(){
        return dao.getAll();
    }

    @GET
    @Path("/{id}")
    public Exercise getById(@PathParam("id")String id){
        return dao.getById(id);
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

        int test = dao.create(exercise);
        return Response.ok(test).build();
    }

    @PUT
    public Response update(Exercise exercise){

        int test = dao.update(exercise);

        return null;

    }



}
