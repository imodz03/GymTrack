package com.elliotb.DAO;

import com.elliotb.Entity.Workout;
import com.elliotb.Entity.mappers.WorkoutMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;
import org.skife.jdbi.v2.sqlobject.stringtemplate.UseStringTemplate3StatementLocator;
import org.skife.jdbi.v2.unstable.BindIn;

import java.util.List;

import static com.elliotb.Helpers.Constants.WORKOUT;

@UseStringTemplate3StatementLocator
public interface WorkoutDAO {

    @Mapper(WorkoutMapper.class)
    @SqlQuery("SELECT * FROM " + WORKOUT + " WHERE public = true ORDER BY dateOfWorkout;")
    List<Workout> getAll();

    @Mapper(WorkoutMapper.class)
    @SqlQuery("SELECT * FROM " + WORKOUT + " WHERE UserID = :id ORDER BY dateOfWorkout;")
    List<Workout> getMine(@Bind("id") String uid);

    @Mapper(WorkoutMapper.class)
    @SqlQuery("SELECT * FROM " + WORKOUT + " WHERE WorkoutID = :id")
    Workout getByID(@Bind("id")String id);

    @SqlUpdate("INSERT INTO " + WORKOUT + "(WorkoutID, workoutName, description, public, dateOfWorkout, UserID, Exercises, SetsID) values(:workout.workoutID, :workout.workoutName, :workout.description, :workout.public, :workout.date, :userID, :elID, :SetsID)")
    int create(@BindBean("workout")Workout workout, @Bind("userID")String userID, @Bind("elID")String elID, @Bind("SetsID")String setsid);

    @SqlUpdate("UPDATE " + WORKOUT + " SET workoutName = :workout.workoutName, description = :workout.description, public = :workout.public, dateOfWorkout = :workout.date, UserID = :userID, Exercises = :elID WHERE WorkoutID = :id")
    int update(@BindBean("workout")Workout workout, @Bind("userID")String userID, @Bind("elID")String elID, @Bind("id")String workoutID);

    @SqlUpdate("DELETE FROM " + WORKOUT + " WHERE WorkoutID = :id")
    int delete(@Bind("id")String id);

    @SqlQuery("SELECT * FROM " + WORKOUT + " WHERE WorkoutID in (<idList>) ORDER BY dateOfWorkout;")
    @Mapper(WorkoutMapper.class)
    List<Workout> getFromIdList(@BindIn("idList")List<String> ids);

}
