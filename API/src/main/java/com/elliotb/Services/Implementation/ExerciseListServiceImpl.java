package com.elliotb.Services.Implementation;

import com.elliotb.DAO.ExerciseListDAO;
import com.elliotb.Entity.ExerciseList;
import com.elliotb.Helpers.UUID;
import com.elliotb.Services.ExerciseListService;
import com.google.inject.Inject;

public class ExerciseListServiceImpl implements ExerciseListService {

    @Inject
    private ExerciseListDAO dao;

    @Override
    public boolean createELEntry(ExerciseList exerciseList) {

        if (exerciseList.getExerciseSize() == 0){
            return false;
        }

        boolean success = true;

        exerciseList.setELID(UUID.getUUID());

        System.out.println(exerciseList);

        for (int i = 0; i < exerciseList.getExerciseSize(); i++){

            int res = dao.create(exerciseList.getELID(), exerciseList.getExercise(i).getExerciseID());

            if (res == 0){
                success = false;
            }

        }

        return success;
    }

}
