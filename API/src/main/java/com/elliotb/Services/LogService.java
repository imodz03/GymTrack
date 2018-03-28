package com.elliotb.Services;

import com.elliotb.Entity.Set;

import java.util.List;

public interface LogService {
    int quickLog(String setsID, String workoutID, String userID);

    int createLog(String workoutID, List<Set> sets, String userID);
}
