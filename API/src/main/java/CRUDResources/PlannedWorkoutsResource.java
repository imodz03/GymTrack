package CRUDResources;

import DAO.PlannedWorkoutsDAO;
import Entity.PlannedWorkouts;
import com.google.inject.Inject;

import javax.ws.rs.core.Response;

public class PlannedWorkoutsResource implements ICRUDResource<PlannedWorkouts> {

    @Inject
    private PlannedWorkoutsDAO dao;

    @Override
    public Response getAll() {
        return null;
    }

    @Override
    public Response getByID(String id) {
        return null;
    }

    @Override
    public Response create(PlannedWorkouts plannedWorkouts) {
        return null;
    }

    @Override
    public Response update(String id, PlannedWorkouts plannedWorkouts) {
        return null;
    }

    @Override
    public Response delete(String id) {
        return null;
    }
}
