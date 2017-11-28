package Entity.enums;

public enum ExerciseCategory {

    AEROBIC("Aerobic"),
    ANAEROBIC("Anaerobic"),
    STRENGTH_TRAINING("Strength Training"),
    STRETCHING("Stretching"),
    BALANCING("Balancing");

    private String val;

    ExerciseCategory(String val){
        this.val = val;
    }

}
