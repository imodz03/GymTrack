package Entity;

import java.util.ArrayList;
import java.util.List;

public class ExerciseList {

    private String ELID;
    private List<Exercise> exercises;

    public ExerciseList(){
        this.exercises = new ArrayList<>();
    }

    public ExerciseList(String ELID) {
        this();
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

    public Exercise getExercise(int pos){
        return exercises.get(pos);
    }

    public void addExercise(Exercise exercise){
        exercises.add(exercise);
    }

    public int getExerciseSize(){
        return exercises.size();
    }

    @Override
    public String toString() {
        return "ExerciseList{" +
                "ELID='" + ELID + '\'' +
                ", exercises=" + exercises +
                '}';
    }
}
