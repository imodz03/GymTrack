package DAO;

import Entity.Log;
import Entity.mappers.LogMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;

import static Helpers.Constants.LOG;

public interface LogDAO {

    @Mapper(LogMapper.class)
    @SqlQuery("SELECT * FROM " + LOG + ";")
    List<Log> getAll();

    @Mapper(LogMapper.class)
    @SqlQuery("SELECT * FROM " + LOG + " WHERE LogID = :id")
    Log getById(@Bind("id")String id);

    @SqlUpdate("INSERT INTO " + LOG + " VALUES(:LogID, :SetID, :WorkoutID, :UserID);")
    int create(@Bind("LogID")String logID, @Bind("SetID")String setID, @Bind("WorkoutID")String workoutID, @Bind("UserID")String UserID);

    @SqlUpdate("UPDATE " + LOG + " SET SetID = :SetID, WorkoutID = :WorkoutID, UserID = :UserID where LogID = :LogID;")
    int update(@Bind("LogID")String logID, @Bind("SetID")String setID, @Bind("WorkoutID")String workoutID, @Bind("UserID")String UserID);

    @SqlUpdate("DELETE FROM " + LOG + " WHERE LogID = :LogID")
    int delete(@Bind("LogID")String logID);

}
