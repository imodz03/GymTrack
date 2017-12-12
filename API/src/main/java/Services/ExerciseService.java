package Services;

import Entity.Exercise;

import java.util.List;

public interface ExerciseService {

    List<Exercise> populateExercises(List<Exercise> exercises);

    Exercise populateExercise(Exercise exercise);

}
