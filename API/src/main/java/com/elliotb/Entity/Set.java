package com.elliotb.Entity;

public class Set {

    //this id will be shared accross multiple entities
    private String SUID;
    private String SetID;
    private Exercise exercise;
    private int position;
    private int reps;
    private double weight;
    private double time;
    private double speed;
    private double distance;

    public Set(){}

    public Set(String setID){
        this();
        this.SetID = setID;
    }

    public String getSetID() {
        return SetID;
    }

    public void setSetID(String setID) {
        SetID = setID;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public String getWeight() {
        return weight+"";
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "Set{\n" +
                "SetID='" + SetID + '\'' +
                "\n, exercise=" + exercise.getExerciseID() +
                "\n, position=" + position +
                "\n, reps=" + reps +
                "\n, weight=" + weight +
                "\n, time=" + time +
                "\n, speed=" + speed +
                "\n, distance=" + distance +
                "\n, SUID=" + SUID +
                '}';
    }

    public String getSUID() {
        return SUID;
    }

    public void setSUID(String SUID) {
        this.SUID = SUID;
    }
}
