package com.elliotb.Services;

import com.elliotb.Entity.PlannedWorkouts;

import java.util.List;

public interface PlanService {
    int addWorkouts(List<PlannedWorkouts> pws, String pid);
    int deleteWorkout(String id);
    int deletePlan(String id);
}
