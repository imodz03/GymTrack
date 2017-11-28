package Entity;

import java.util.List;

public class ExerciseList {

    private String ELID;
    private List<Exercise> exercises;

    public ExerciseList(){}

    public ExerciseList(String ELID) {
        this.ELID = ELID;
    }

    public String getELID() {
        return ELID;
    }

    public void setELID(String ELID) {
        this.ELID = ELID;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }
}
