package com.elliotb.DAO;

import com.elliotb.Entity.Exercise;
import com.elliotb.Entity.mappers.ExerciseMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;

import static com.elliotb.Helpers.Constants.EXERCISE;

public interface ExerciseDAO {

    @Mapper(ExerciseMapper.class)
    @SqlQuery("select * from " + EXERCISE + ";")
    List<Exercise> getAll();

    @Mapper(ExerciseMapper.class)
    @SqlQuery("select * from " + EXERCISE + " where ExerciseLibraryID = :id")
    Exercise getById(@Bind("id")String id);

    @SqlUpdate("delete from " + EXERCISE + " where ExerciseLibraryID = :id")
    int delete(@Bind("id")String id);

    @SqlUpdate("insert into " + EXERCISE + "(ExerciseLibraryID, exerciseName, description, category, bodypart) values(:exercise.exerciseID, :exercise.exerciseName, :exercise.description, :exercise.exerciseCategory, :exercise.bodypart)")
    int create(@BindBean("exercise") Exercise exercise);

    @SqlUpdate("update " + EXERCISE + " set exerciseName = :exercise.exerciseName, description = :exercise.description, category = :exercise.exerciseCategory, bodypart = :exercise.bodypart where ExerciseLibraryID = :id")
    int update(@BindBean("exercise") Exercise exercise, @Bind("id")String id);

}