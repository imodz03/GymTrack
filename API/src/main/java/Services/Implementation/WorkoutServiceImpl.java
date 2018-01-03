package Services.Implementation;

import DAO.WorkoutDAO;
import Entity.Workout;
import Services.WorkoutService;
import com.google.inject.Inject;

public class WorkoutServiceImpl implements WorkoutService {

    @Inject
    private WorkoutDAO dao;

    @Override
    public Workout populate(Workout workout) {
        Workout populate = dao.getByID(workout.getWorkoutID());
        return populate;
    }
}
