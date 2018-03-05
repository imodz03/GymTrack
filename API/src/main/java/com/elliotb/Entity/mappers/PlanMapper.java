package com.elliotb.Entity.mappers;

import com.elliotb.Entity.Plan;
import com.elliotb.Entity.User;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlanMapper implements ResultSetMapper<Plan> {

    @Override
    public Plan map(int index, ResultSet r, StatementContext ctx) throws SQLException {

        Plan mappable = new Plan(r.getString(1), r.getString(2));
        User mappableUser = new User(r.getString(4));

        mappable.setDescription(r.getString(3));
        mappable.setUser(mappableUser);
        mappable.setPublic(r.getBoolean(5));
        mappable.setRepeats(r.getInt(6));

        return mappable;
    }
}
