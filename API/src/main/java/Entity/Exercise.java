package Entity;

import Entity.enums.Bodypart;
import Entity.enums.ExerciseCategory;

public class Exercise {

    private String exerciseID;
    private String exerciseName;
    private String description;
    private Bodypart bodypart;
    private ExerciseCategory exerciseCategory; // for strength training the value posted needs to be strength_training

    public Exercise(){}

    public Exercise(String id){
        this();
        this.exerciseID = id;
    }

    public Exercise(String exerciseID, String exerciseName, String description, Bodypart bodypart, ExerciseCategory exerciseCategory) {
        this.exerciseID = exerciseID;
        this.exerciseName = exerciseName;
        this.description = description;
        this.bodypart = bodypart;
        this.exerciseCategory = exerciseCategory;
    }

    public String getExerciseID() {
        return exerciseID;
    }

    public void setExerciseID(String exerciseID) {
        this.exerciseID = exerciseID;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Bodypart getBodypart() {
        return bodypart;
    }

    public void setBodypart(Bodypart bodypart) {
        this.bodypart = bodypart;
    }

    public String getExerciseCategory() {
        return exerciseCategory.getVal();
    }

    public void setExerciseCategory(ExerciseCategory exerciseCategory) {
        this.exerciseCategory = exerciseCategory;
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "\nexerciseID='" + exerciseID + '\'' +
                ", \nexerciseName='" + exerciseName + '\'' +
                ", \ndescription='" + description + '\'' +
                ", \nbodypart=" + bodypart +
                ", \nexerciseCategory=" + exerciseCategory +
                "\n}";
    }
}
