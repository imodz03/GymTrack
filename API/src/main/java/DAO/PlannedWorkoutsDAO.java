package DAO;

import Entity.PlannedWorkouts;
import Entity.mappers.PlannedWorkoutsMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;

import static Helpers.Constants.PLWORKOUTS;

public interface PlannedWorkoutsDAO {

    @Mapper(PlannedWorkoutsMapper.class)
    @SqlQuery("SELECT * FROM " + PLWORKOUTS)
    List<PlannedWorkouts> getAll();

    @Mapper(PlannedWorkoutsMapper.class)
    @SqlQuery("SELECT * FROM " + PLWORKOUTS + " WHERE PWID = :id")
    PlannedWorkouts getById(@Bind("id")String id);

    @SqlUpdate("INSERT INTO " + PLWORKOUTS + " (PWID, PlanID, WorkoutID, WorkoutDay, WorkoutDOW) values(:pw.pwID, :pw.planID, :WorkoutID, :pw.workoutDay, :pw.dayOfWeek)")
    int create(@BindBean("pw")PlannedWorkouts plannedWorkouts, @Bind("WorkoutID")String wID);

    @SqlUpdate("UPDATE " + PLWORKOUTS + " SET PlanID = :pw.planID, WorkoutID = :WorkoutID, WorkoutDay = :pw.workoutDay, WorkoutDOW = :pw.dayOfWeek WHERE PWID = :id")
    int update(@BindBean("pw")PlannedWorkouts plannedWorkouts, @Bind("WorkoutID")String wID, @Bind("id")String id);

    @SqlUpdate("DELETE FROM " + PLWORKOUTS + " WHERE PWID = :id")
    int delete(@Bind("id")String id);

}
