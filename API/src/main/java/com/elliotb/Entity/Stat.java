package com.elliotb.Entity;

import org.joda.time.DateTime;

public class Stat {

    private DateTime date;
    private String exerciseID;
    private int position;
    private int reps;
    private double weight;
    private double time;
    private double speed;
    private double distance;

    public Stat(){

    }

    public String getDate() {
        return date.toString("dd-MM-yyyy");
    }

    public DateTime getDateTime(){
        return this.date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public String getExerciseID() {
        return exerciseID;
    }

    public void setExerciseID(String exerciseID) {
        this.exerciseID = exerciseID;
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

    public double getWeight() {
        return weight;
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
}
