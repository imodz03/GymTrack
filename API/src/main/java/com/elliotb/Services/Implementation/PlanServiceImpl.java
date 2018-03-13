package com.elliotb.Services.Implementation;

import com.elliotb.DAO.PlanDAO;
import com.elliotb.DAO.PlannedWorkoutsDAO;
import com.elliotb.DAO.WorkoutDAO;
import com.elliotb.Entity.Plan;
import com.elliotb.Entity.PlannedWorkouts;
import com.elliotb.Entity.Workout;
import com.elliotb.Helpers.UUID;
import com.elliotb.Services.PlanService;
import com.google.inject.Inject;
import org.joda.time.DateTime;

import java.util.List;

public class PlanServiceImpl implements PlanService {

    @Inject
    private PlanDAO planDAO;

    @Inject
    private PlannedWorkoutsDAO pwDAO;

    @Inject
    private WorkoutDAO workoutDAO;

    @Override
    public int addWorkouts(List<PlannedWorkouts> pws, String pid) {

        Plan p = planDAO.getById(pid);
        int repeats = p.getRepeats();
        DateTime dateTime = new DateTime(p.getStartDate());
        DateTime dt;
        int res1 = 0;
        int res2 = 0;
        int res3 = 0;

        if (pws.get(0).getDayOfWeek() != null){
            for (int i = 0; i < repeats * 7; i++){

                dt = dateTime.plusDays(i);

                for (PlannedWorkouts pw : pws) {

                    if (pw.getRawDOW().getNum() == dt.getDayOfWeek()){
                        String uuid1 = UUID.getUUID();
                        String uuid2 = UUID.getUUID();
                        Workout temp = workoutDAO.getByID(pw.getWorkout().getWorkoutID());

                        temp.setWorkoutID(uuid1);
                        temp.setDate(dt);

                        pw.setPwID(uuid2);

                        res1 = workoutDAO.create(temp, temp.getUser().getUserID(), temp.getExerciseList().getELID());
                        res2 = pwDAO.create(pw, uuid1);

                    }

                }

            }
        }

        if (res1 == 1 && res2 == 1){
            return 1;
        }else{
            return 0;
        }
    }
}
