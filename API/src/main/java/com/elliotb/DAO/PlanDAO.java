package com.elliotb.DAO;

import com.elliotb.Entity.Plan;
import com.elliotb.Entity.mappers.PlanMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;

import static com.elliotb.Helpers.Constants.PLAN;

public interface PlanDAO {

    @Mapper(PlanMapper.class)
    @SqlQuery("SELECT * FROM " + PLAN + ";")
    List<Plan> getAll();

    @Mapper(PlanMapper.class)
    @SqlQuery("SELECT * FROM " + PLAN + " WHERE PlanID = :id")
    Plan getById(@Bind("id")String id);

    @SqlUpdate("INSERT INTO " + PLAN + " (PlanID, PlanName, Description, UserID, Public, Repeats) VALUES (:plan.planID, :plan.planName, :plan.description, :userID, :plan.public, :plan.repeats)")
    int create(@BindBean("plan") Plan plan, @Bind("userID") String userID);

    @SqlUpdate("UPDATE " + PLAN + " SET PlanName = :plan.planName, Description = :plan.description, UserID = :userID, Public = :plan.public, Repeats = :plan.repeats WHERE PlanID = :id")
    int update(@BindBean("plan") Plan plan, @Bind("userID")String userID, @Bind("id")String id);

    @SqlUpdate("DELETE FROM " + PLAN + " WHERE PlanID = :id")
    int delete(@Bind("id")String id);

}
