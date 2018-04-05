package com.elliotb.Entity.mappers;

import com.elliotb.Entity.Stat;
import org.joda.time.DateTime;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StatsMapper implements ResultSetMapper<Stat> {

    @Override
    public Stat map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        Stat mappable = new Stat();

        mappable.setDate(new DateTime(r.getDate(1)).plusDays(1));
        mappable.setExerciseID(r.getString(2));
        mappable.setPosition(r.getInt(3));
        mappable.setReps(r.getInt(4));
        mappable.setWeight(r.getInt(5));
        mappable.setTime(r.getDouble(6));
        mappable.setDistance(r.getDouble(7));

        return mappable;
    }
}
