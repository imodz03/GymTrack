package CRUDResources;

import DAO.GoalDAO;
import Entity.Goal;
import Helpers.UUID;
import com.google.inject.Inject;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;


@Produces("Application/JSON")
@Consumes("Application/JSON")
@Path("/API/resource/goal")
public class GoalResource implements ICRUDResource<Goal>{

    @Inject
    private GoalDAO dao;

    @GET
    public Response getAll() {
        return Response.ok(dao.getAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response getByID(@PathParam("id")String id) {
        return Response.ok(dao.getById(id)).build();
    }

    @POST
    public Response create(Goal goal) {
        if (goal.getGoalID().isEmpty()){
            goal.setGoalID(UUID.getUUID());
        }

        System.out.println(goal);

        //dao.create(goal, goal.getSet().getSetID(), goal.getUser().getUserID());

        return null;
    }

    @Override
    public Response update(String id, Goal goal) {
        return null;
    }

    @Override
    public Response delete(String id) {
        return null;
    }
}
