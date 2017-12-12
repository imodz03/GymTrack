package DAO;

import java.util.List;
import Entity.Set;
import Entity.mappers.SetsMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import static Helpers.Constants.SET;

public interface SetDAO {

    @Mapper(SetsMapper.class)
    @SqlQuery("select * from " + SET)
    List<Set> getAll();

    @Mapper(SetsMapper.class)
    @SqlQuery("select * from " + SET + " where SetsID = :id")
    Set getById(@Bind("id")String id);

}
