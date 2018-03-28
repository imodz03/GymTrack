package com.elliotb.Services.Implementation;

import com.elliotb.DAO.LogDAO;
import com.elliotb.DAO.SetDAO;
import com.elliotb.Entity.Log;
import com.elliotb.Entity.Set;
import com.elliotb.Helpers.UUID;
import com.elliotb.Services.LogService;
import com.google.inject.Inject;

import java.util.List;

public class LogServiceImpl implements LogService {

    @Inject
    private SetDAO setDAO;

    @Inject
    private LogDAO logDAO;


    @Override
    public int quickLog(String setsID, String workoutID, String userID) {

        String LogID = UUID.getUUID();

        int resp = logDAO.create(LogID, setsID, workoutID, userID);

        return resp;
    }

    @Override
    public int createLog(String workoutID, List<Set> sets, String userID){
        int resp = 0;

        String setID = UUID.getUUID();
        String logID = UUID.getUUID();

        for (Set set : sets) {

            set.setSUID(UUID.getUUID());
            set.setSetID(setID);

            resp = setDAO.create(set, set.getExercise().getExerciseID(), set.getSUID());

        }

        if (resp == 1){
            resp = logDAO.create(logID, setID, workoutID, userID);
        }

        return resp;
    }
}
