package com.elliotb.Entity.mappers;

import com.elliotb.Entity.Goal;
import com.elliotb.Entity.Set;
import com.elliotb.Entity.User;
import org.joda.time.DateTime;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoalMapper implements ResultSetMapper<Goal> {

    public Goal map(int index, ResultSet r, StatementContext ctx) throws SQLException {

        Goal mappable = new Goal(r.getString(1));

        Set mappableSet = new Set(r.getString(2));

        User mappableUser = new User(r.getString(3));

        List<Set> sets = new ArrayList<>();
        sets.add(mappableSet);

        mappable.setSet(sets);
        mappable.setUser(mappableUser);

        if (r.getDate(4) != null){
            mappable.setTargetDate(new DateTime(r.getDate(4)));
        }else{
            mappable.setTargetDate(new DateTime(0));
        }

        if (r.getDate(5) != null){
            mappable.setDateAchieved(new DateTime(r.getDate(5)));
        }else{
            //if the goal has not been achieved the date will default to 0, will handle display of this in front end
            mappable.setDateAchieved(new DateTime(0));
        }

        return mappable;
    }
}
