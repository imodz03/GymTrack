package Services.Implementation;

import DAO.ExerciseListDAO;
import DAO.WorkoutDAO;
import Entity.ExerciseList;
import Entity.Workout;
import Helpers.OneToManyCombiner;
import Services.ExerciseService;
import Services.WorkoutService;
import com.google.inject.Inject;

import java.util.List;

public class WorkoutServiceImpl implements WorkoutService {

    @Inject
    private WorkoutDAO dao;

    @Inject
    private ExerciseListDAO elDao;

    @Inject
    private ExerciseService exerciseService;

    @Override
    public Workout populate(Workout workout) {
        Workout populate = dao.getByID(workout.getWorkoutID());
        return populate;
    }

    @Override
    public void populateExercise(Workout workout) {

        List<ExerciseList> list = elDao.getById(workout.getExerciseList().getELID());

        if (list.size() > 0){
            list = OneToManyCombiner.combineExercises(list);
            for (ExerciseList exerciseList : list) {
                exerciseService.populateExercises(exerciseList.getExercises());
            }
        }

        workout.setExerciseList(list.get(0));

    }
}
