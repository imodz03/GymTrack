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
}
