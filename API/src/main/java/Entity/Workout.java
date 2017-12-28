package Entity;

import Entity.enums.ExerciseCategory;
import org.joda.time.DateTime;

import java.util.List;

public class Workout {

    private String WorkoutID;
    private String WorkoutName;
    private ExerciseList exerciseList;
    private String description;
    private ExerciseCategory exerciseCategory;
    private List<Set> sets;
    private User user;
    private boolean isPublic;
    private DateTime date;

    public Workout(){}

    public Workout(String id){
        this.WorkoutID = id;
    }

    public String getWorkoutID() {
        return WorkoutID;
    }

    public void setWorkoutID(String workoutID) {
        WorkoutID = workoutID;
    }

    public String getWorkoutName() {
        return WorkoutName;
    }

    public void setWorkoutName(String workoutName) {
        WorkoutName = workoutName;
    }

    public ExerciseList getExerciseList() {
        return exerciseList;
    }

    public void setExerciseList(ExerciseList exerciseList) {
        this.exerciseList = exerciseList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ExerciseCategory getExerciseCategory() {
        return exerciseCategory;
    }

    public void setExerciseCategory(ExerciseCategory exerciseCategory) {
        this.exerciseCategory = exerciseCategory;
    }

    public List<Set> getSets() {
        return sets;
    }

    public void setSets(List<Set> sets) {
        this.sets = sets;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public String getDate() {
        return date.toString("yyyy-MM-dd");
    }

    public void setDate(DateTime date) {
        this.date = date;
    }
}
