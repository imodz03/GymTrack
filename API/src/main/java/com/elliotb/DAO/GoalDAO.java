package com.elliotb.DAO;

import com.elliotb.Entity.Goal;
import com.elliotb.Entity.mappers.GoalMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;

import static com.elliotb.Helpers.Constants.GOAL;

public interface GoalDAO {

    @Mapper(GoalMapper.class)
    @SqlQuery("SELECT GoalID, SetID, UserID, targetDate, dateAchieved, GoalName FROM " + GOAL + ";")
    List<Goal> getAll();

    @Mapper(GoalMapper.class)
    @SqlQuery("SELECT GoalID, SetID, UserID, targetDate, dateAchieved, GoalName FROM " + GOAL + " where UserID = :id;")
    List<Goal> getMine(@Bind("id")String userID);

    @Mapper(GoalMapper.class)
    @SqlQuery("SELECT GoalID, SetID, UserID, targetDate, dateAchieved, GoalName FROM " + GOAL + " Where GoalID = :id")
    Goal getById(@Bind("id")String id);

    @SqlUpdate("INSERT INTO " + GOAL + " (GoalID, SetID, UserID, targetDate, dateAchieved, GoalName) values(:goalID, :setID, :userID, :goal.targetDate, :goal.dateAchieved, :goal.goalName)")
    int create(@Bind("goalID")String goalID, @BindBean("goal")Goal goal, @Bind("setID")String setID, @Bind("userID")String userID);

    @SqlUpdate("UPDATE " + GOAL + " SET SetID = :setID, targetDate = :goal.targetDate, dateAchieved = :goal.dateAchieved where GoalID = :id")
    int update(@Bind("id")String goalID, @Bind("setID")String setID, @BindBean("goal")Goal goal);

    @SqlUpdate("DELETE FROM " + GOAL + " Where GoalID = :id")
    int delete(@Bind("id")String goalID);

    @SqlUpdate("UPDATE " + GOAL + " SET dateAchieved = :date where GoalID = :id")
    int complete(@Bind("id")String goalID, @Bind("date")String date);

}
