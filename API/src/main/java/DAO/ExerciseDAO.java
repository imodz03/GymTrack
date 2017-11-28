package DAO;

import Entity.Exercise;
import Entity.mappers.ExerciseMapper;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;

public interface ExerciseDAO {

    @Mapper(ExerciseMapper.class)
    @SqlQuery("select * from ExerciseLibrary;")
    public List<Exercise> getAll();

}