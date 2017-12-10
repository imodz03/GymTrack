package CRUDResources;

import DAO.ExerciseListDAO;
import Entity.ExerciseList;
import Helpers.OneToManyCombiner;
import com.google.inject.Inject;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Produces("Application/JSON")
@Consumes("Application/JSON")
@Path("/API/resource/el")
public class ExerciseListResource implements ICRUDResource<ExerciseList> {

    @Inject
    private ExerciseListDAO dao;

    @GET
    public Response getAll() {

        List<ExerciseList> list = dao.getAll();

        List<ExerciseList> returnable = new ArrayList<>();

        if (list.size() > 0){
            returnable = OneToManyCombiner.combineExercises(list);

        }

        System.out.println(returnable);

        return Response.ok(returnable).build();
    }

    @Override
    public Response getByID(String id) {
        return null;
    }

    @Override
    public Response create(ExerciseList exerciseList) {
        return null;
    }

    @Override
    public Response update(String id, ExerciseList exerciseList) {
        return null;
    }

    @Override
    public Response delete(String id) {
        return null;
    }
}
