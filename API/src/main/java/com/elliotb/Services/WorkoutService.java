package com.elliotb.Services;

import com.elliotb.Entity.Workout;

public interface WorkoutService {

    Workout populate(Workout workout);

    void populateExercise(Workout workout);

}
