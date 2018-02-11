package DAO;

import Entity.Log;
import Entity.mappers.LogMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
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

}
