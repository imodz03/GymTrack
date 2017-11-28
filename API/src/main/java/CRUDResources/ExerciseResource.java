package CRUDResources;

import DAO.ExerciseDAO;
import Entity.Exercise;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

@Produces("Application/JSON")
@Path("/API/resource/exercise")
public class ExerciseResource {

    private ExerciseDAO dao;

    public ExerciseResource(ExerciseDAO dao){
        this.dao = dao;
    }

    @GET
    public List<Exercise> getAll(){
        return dao.getAll();
    }

}
