package com.elliotb.Entity;

import com.elliotb.Entity.enums.DoW;

public class PlannedWorkouts {

    private String pwID;
    private String planID;
    private Workout workout;
    private int workoutDay;
    private DoW dayOfWeek;

    public PlannedWorkouts(){}

    public PlannedWorkouts(String pwID){
        this.pwID = pwID;
    }

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

    public String getDayOfWeek() {
        return dayOfWeek.getVal();
    }

    public void setDayOfWeek(DoW dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getPlanID() {
        return planID;
    }

    public void setPlanID(String planID) {
        this.planID = planID;
    }

    @Override
    public String toString() {
        return "PlannedWorkouts{" +
                "pwID='" + pwID + '\'' +
                ", planID='" + planID + '\'' +
                ", workout=" + workout.getWorkoutID() +
                ", workoutDay=" + workoutDay +
                ", dayOfWeek=" + dayOfWeek +
                '}';
    }
}
