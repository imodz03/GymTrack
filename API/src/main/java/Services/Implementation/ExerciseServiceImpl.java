package Services.Implementation;

import DAO.ExerciseDAO;
import Entity.Exercise;
import Services.ExerciseService;
import com.google.inject.Inject;

import java.util.List;

public class ExerciseServiceImpl implements ExerciseService {

    @Inject
    private ExerciseDAO dao;

    public ExerciseServiceImpl(){}

    public List<Exercise> populateExercises(List<Exercise> list){

        for (int i =0; i < list.size(); i++){
            list.set(i, dao.getById(list.get(i).getExerciseID()));
        }

        return list;

    }

}
