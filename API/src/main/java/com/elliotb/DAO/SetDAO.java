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
    @SqlQuery("select * from " + SET)
    List<Set> getAll();

    // TODO: 10/02/2018 get by id needs to return a list ordered by position
    @Mapper(SetsMapper.class)
    @SqlQuery("select * from " + SET + " where SetsID = :id")
    Set getById(@Bind("id")String id);

    @SqlUpdate("INSERT into " + SET + " (SetsID, ExerciseID, position, reps, weight) values(:set.setID, :exerciseID, :set.position, :set.reps, :set.weight)")
    int create(@BindBean("set")Set set, @Bind("exerciseID")String exID);

    @SqlUpdate("UPDATE " + SET + " set ExerciseID = :exID, position = :set.position, reps = :set.reps, weight = :set.weight where SetsID = :id")
    int update(@Bind("id")String id,@Bind("exID")String exID, @BindBean("set")Set set);

    @SqlUpdate("DELETE from " + SET + " where SetsID = :id")
    int delete(@Bind("id")String id);

}
