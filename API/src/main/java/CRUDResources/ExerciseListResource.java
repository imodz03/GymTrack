package CRUDResources;

import DAO.ExerciseListDAO;
import Entity.ExerciseList;
import com.google.inject.Inject;

import javax.ws.rs.core.Response;

public class ExerciseListResource implements ICRUDResource<ExerciseList> {

    @Inject
    private ExerciseListDAO dao;

    @Override
    public Response getAll() {
        return null;
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
