package com.elliotb.DAO;

import com.elliotb.Entity.ExerciseList;
import com.elliotb.Entity.mappers.ExerciseListMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;

import static com.elliotb.Helpers.Constants.EXERCISELIST;

public interface ExerciseListDAO {

    @Mapper(ExerciseListMapper.class)
    @SqlQuery("Select * from " + EXERCISELIST + " order by ExerciseListID;")
    List<ExerciseList> getAll();

    @Mapper(ExerciseListMapper.class)
    @SqlQuery("Select * from " + EXERCISELIST + " where ExerciseListID = :id")
    List<ExerciseList> getById(@Bind("id")String id);

    //update isn't really applicable to exerciseList
    int update(String id, ExerciseList exerciseList);

    @SqlUpdate("insert into " + EXERCISELIST + "(ExerciseListID, ExerciseID) values(:elid, :eleid)")
    int create(@Bind("elid")String elid, @Bind("eleid")String eleid);

    @SqlUpdate("delete from " + EXERCISELIST + " Where ExerciseListID = :id")
    int delete(@Bind("id")String id);

    @SqlUpdate("delete from " + EXERCISELIST + " WHERE ExerciseListID = :id AND ExerciseID = :exId")
    int deleteExercise(@Bind("id")String id, @Bind("exId")String exerciseID);

    @SqlUpdate("INSERT INTO " + EXERCISELIST + " values(:id, :exId)")
    int add(@Bind("id")String id, @Bind("exId")String exId);

}
