package com.elliotb.Services.Implementation;

import com.elliotb.DAO.SetDAO;
import com.elliotb.Entity.Set;
import com.elliotb.Services.SetService;
import com.google.inject.Inject;

import java.util.List;

public class SetServiceImpl implements SetService {

    @Inject
    SetDAO setDAO;

    @Override
    public int deleteSet(String suid) {

        int res = 0;
        Set s = setDAO.getBySUID(suid);
        List<Set> inList = setDAO.getByIdAndEx(s.getSetID(), s.getExercise().getExerciseID());

        res = setDAO.delete(suid);

        int count = 1;
        if(res != 0){
            //re order count if delete was successful
            for (int i = 0; i < inList.size(); i++){
                Set temp = inList.get(i);
                if (!temp.getSUID().equals(suid)){
                    temp.setPosition(count);
                    res = setDAO.update(temp.getSUID(), temp.getExercise().getExerciseID(), temp);
                    count++;
                }
            }
        }
        return res;

    }
}
