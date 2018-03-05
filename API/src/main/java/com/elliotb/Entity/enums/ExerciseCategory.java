package com.elliotb.Entity.enums;

public enum ExerciseCategory {

    AEROBIC("Aerobic"),
    ANAEROBIC("Anaerobic"),
    STRENGTH_TRAINING("Strength Training"),
    STRETCHING("Stretching"),
    BALANCING("Balancing"),
    DEFAULT("Default");

    private String val;

    public String getVal(){
        return val;
    }

    ExerciseCategory(String val){
        this.val = val;
    }

}
