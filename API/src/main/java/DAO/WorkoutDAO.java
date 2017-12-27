package DAO;

import Entity.Workout;
import Entity.mappers.WorkoutMapper;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;

import static Helpers.Constants.WORKOUT;

public interface WorkoutDAO {

    @Mapper(WorkoutMapper.class)
    @SqlQuery("SELECT * FROM " + WORKOUT + ";")
    List<Workout> getAll();

}
