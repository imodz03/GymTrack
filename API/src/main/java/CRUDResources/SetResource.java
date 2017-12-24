package CRUDResources;

import DAO.SetDAO;
import Entity.Exercise;
import Entity.Set;
import Helpers.UUID;
import Services.ExerciseService;
import com.google.inject.Inject;

import javax.ws.rs.*;
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

        Set resp = dao.getById(id);

        Exercise exer = exerciseService.populateExercise(resp.getExercise());

        resp.setExercise(exer);

        return Response.ok(resp).build();
    }

    // TODO: 24/12/2017 proper response codes
    @POST
    public Response create(Set set) {

        set.setSetID(UUID.getUUID());

        int res = dao.create(set, set.getExercise().getExerciseID());

        return Response.ok(res).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id")String id, Set set) {
        System.out.println(set);
        int res = dao.update(id, set.getExercise().getExerciseID(), set);
        return Response.ok(res).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id")String id) {
        return Response.ok(dao.delete(id)).build();
    }
}
