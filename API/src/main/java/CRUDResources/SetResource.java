package CRUDResources;

import DAO.SetDAO;
import Entity.Exercise;
import Entity.Set;
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
                System.out.println(dbre);
            }
        }

        return Response.ok(dao.getAll()).build();
    }

    @Override
    @GET
    @Path("/{id}")
    public Response getByID(@PathParam("id")String id) {
        return Response.ok(dao.getById(id)).build();
    }

    @Override
    public Response create(Set set) {
        return null;
    }

    @Override
    public Response update(String id, Set set) {
        return null;
    }

    @Override
    public Response delete(String id) {
        return null;
    }
}
