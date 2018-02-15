package DAO;

import Entity.Goal;
import Entity.mappers.GoalMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;

import static Helpers.Constants.GOAL;

public interface GoalDAO {

    @Mapper(GoalMapper.class)
    @SqlQuery("SELECT * FROM " + GOAL + ";")
    List<Goal> getAll();

    @Mapper(GoalMapper.class)
    @SqlQuery("SELECT * FROM " + GOAL + " Where GoalID = :id")
    Goal getById(@Bind("id")String id);

    @SqlUpdate("INSERT INTO " + GOAL + " (GoalID, SetID, UserID, targetDate, dateAchieved) values(:goalID, :setID, :userID, :goal.targetDate, :goal.dateAchieved)")
    int create(@Bind("goalID")String goalID, @BindBean("goal")Goal goal, @Bind("setID")String setID, @Bind("userID")String userID);

    @SqlUpdate("UPDATE " + GOAL + " SET SetID = :setID, targetDate = :goal.targetDate, dateAchieved = :goal.dateAchieved where GoalID = :id")
    int update(@Bind("id")String goalID, @Bind("setID")String setID, @BindBean("goal")Goal goal);

    @SqlUpdate("DELETE FROM " + GOAL + " Where GoalID = :id")
    int delete(@Bind("id")String goalID);

}