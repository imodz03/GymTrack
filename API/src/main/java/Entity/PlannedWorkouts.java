package Entity;

import Entity.enums.DoW;

public class PlannedWorkouts {

    private String pwID;
    private Workout workout;
    private int workoutDay;
    private DoW dayOfWeek;

    public PlannedWorkouts(String pwID, Workout workout, int workoutDay) {
        this.pwID = pwID;
        this.workout = workout;
        this.workoutDay = workoutDay;
    }

    public PlannedWorkouts(String pwID, Workout workout, DoW dayOfWeek) {
        this.pwID = pwID;
        this.workout = workout;
        this.dayOfWeek = dayOfWeek;
    }

    public String getPwID() {
        return pwID;
    }

    public void setPwID(String pwID) {
        this.pwID = pwID;
    }

    public Workout getWorkout() {
        return workout;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

    public int getWorkoutDay() {
        return workoutDay;
    }

    public void setWorkoutDay(int workoutDay) {
        this.workoutDay = workoutDay;
    }

    public DoW getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DoW dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
}
