package com.elliotb.DAO;

import java.util.List;
import com.elliotb.Entity.Set;
import com.elliotb.Entity.mappers.SetsMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import static com.elliotb.Helpers.Constants.SET;

public interface SetDAO {

    @Mapper(SetsMapper.class)
    @SqlQuery("select SetsID, ExerciseID, position, reps, weight, timeTaken, distance, SUID from " + SET)
    List<Set> getAll();

    @Mapper(SetsMapper.class)
    @SqlQuery("select SetsID, ExerciseID, position, reps, weight, timeTaken, distance, SUID from " + SET + " where SetsID = :id ORDER BY SetsID, ExerciseID, `position` ASC")
    List<Set> getById(@Bind("id")String id);

    @Mapper(SetsMapper.class)
    @SqlQuery("select SetsID, ExerciseID, position, reps, weight, timeTaken, distance, SUID from " + SET + " where SUID = :id ORDER BY SetsID, ExerciseID, `position` ASC")
    Set getBySUID(@Bind("id")String id);

    @Mapper(SetsMapper.class)
    @SqlQuery("select SetsID, ExerciseID, position, reps, weight, timeTaken, distance, SUID from " + SET + " where SetsID = :id and ExerciseID = :exID ORDER BY SetsID, ExerciseID, `position` ASC")
    List<Set> getByIdAndEx(@Bind("id")String id, @Bind("exID")String exID);

    @SqlUpdate("INSERT into " + SET + " (SetsID, ExerciseID, position, reps, weight, SUID) values(:set.setID, :exerciseID, :set.position, :set.reps, :set.weight, :SUID)")
    int create(@BindBean("set")Set set, @Bind("exerciseID")String exID, @Bind("SUID")String suid);

    @SqlUpdate("UPDATE " + SET + " set ExerciseID = :exID, position = :set.position, reps = :set.reps, weight = :set.weight where SUID = :id")
    int update(@Bind("id")String id,@Bind("exID")String exID, @BindBean("set")Set set);

    @SqlUpdate("DELETE from " + SET + " where SUID = :id")
    int delete(@Bind("id")String id);

}
