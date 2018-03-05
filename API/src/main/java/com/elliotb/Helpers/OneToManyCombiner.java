package com.elliotb.Helpers;

import com.elliotb.Entity.ExerciseList;

import java.util.ArrayList;
import java.util.List;

public class OneToManyCombiner {

    public static List<ExerciseList> combineExercises(List<ExerciseList> exerciseLists){

        List<ExerciseList> finalList = new ArrayList<>();

        finalList.add(exerciseLists.get(0));
        String id = exerciseLists.get(0).getELID();

        for (int i = 1; i < exerciseLists.size(); i++) {

            ExerciseList el = exerciseLists.get(i);
            if (el.getELID().equals(id)){

                finalList.get(finalList.size()-1).addExercise(el.getExercise(0));

            }else{

                finalList.add(el);
                id = el.getELID();

            }

        }
        return finalList;
    }

}
