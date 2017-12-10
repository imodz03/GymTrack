package DAO;

import Entity.ExerciseList;
import Entity.mappers.ExerciseListMapper;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;

import static Helpers.Constants.EXERCISELIST;

public interface ExerciseListDAO {

    @Mapper(ExerciseListMapper.class)
    @SqlQuery("Select * from " + EXERCISELIST + " order by ExerciseListID;")
    List<ExerciseList> getAll();

    ExerciseList getById(String id);

    int update(String id, ExerciseList exerciseList);

    int create(ExerciseList exerciseList);

    int delete(String id);

}
