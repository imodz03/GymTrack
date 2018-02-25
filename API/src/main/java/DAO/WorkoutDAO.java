package DAO;

import Entity.Workout;
import Entity.mappers.WorkoutMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;

import static Helpers.Constants.WORKOUT;

public interface WorkoutDAO {

    @Mapper(WorkoutMapper.class)
    @SqlQuery("SELECT * FROM " + WORKOUT + " WHERE public = true;")
    List<Workout> getAll();

    @Mapper(WorkoutMapper.class)
    @SqlQuery("SELECT * FROM " + WORKOUT + " WHERE UserID = :id;")
    List<Workout> getMine(@Bind("id") String uid);

    @Mapper(WorkoutMapper.class)
    @SqlQuery("SELECT * FROM " + WORKOUT + " WHERE WorkoutID = :id")
    Workout getByID(@Bind("id")String id);

    @SqlUpdate("INSERT INTO " + WORKOUT + "(WorkoutID, workoutName, description, public, dateOfWorkout, UserID, Exercises) values(:workout.workoutID, :workout.workoutName, :workout.description, :workout.public, :workout.date, :userID, :elID)")
    int create(@BindBean("workout")Workout workout, @Bind("userID")String userID, @Bind("elID")String elID);

    @SqlUpdate("UPDATE " + WORKOUT + " SET workoutName = :workout.workoutName, description = :workout.description, public = :workout.public, dateOfWorkout = :workout.date, UserID = :userID, Exercises = :elID WHERE WorkoutID = :id")
    int update(@BindBean("workout")Workout workout, @Bind("userID")String userID, @Bind("elID")String elID, @Bind("id")String workoutID);

    @SqlUpdate("DELETE FROM " + WORKOUT + " WHERE WorkoutID = :id")
    int delete(@Bind("id")String id);

}
