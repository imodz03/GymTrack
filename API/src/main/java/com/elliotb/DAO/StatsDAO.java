package com.elliotb.DAO;

import com.elliotb.Entity.Stat;
import com.elliotb.Entity.mappers.StatsMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;

public interface StatsDAO {

    @SqlQuery("select W.DateOfWorkout, S.ExerciseID, s.`position`, s.reps, s.weight, s.timeTaken, s.distance " +
            "from Logs as L inner join Workout as W on (L.WorkoutID = W.WorkoutID) " +
            "inner join `Sets` as S on (L.SetID = S.SetsID) " +
            "where L.UserID = :userID and S.ExerciseID = :exID " +
            "order by W.DateOfWorkout ASC")
    @Mapper(StatsMapper.class)
    List<Stat> getStats(@Bind("exID")String exerciseID, @Bind("userID")String userID);

}
