package com.elliotb.Entity.mappers;

import com.elliotb.Entity.ExerciseList;
import com.elliotb.Entity.Set;
import com.elliotb.Entity.User;
import com.elliotb.Entity.Workout;
import org.joda.time.DateTime;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WorkoutMapper implements ResultSetMapper<Workout> {

    @Override
    public Workout map(int index, ResultSet r, StatementContext ctx) throws SQLException {

        Workout mappable = new Workout(r.getString(1));

        ExerciseList mappableEl = new ExerciseList(r.getString(2));
        Set mappableSet = new Set(r.getString(6));
        List<Set> setList = new ArrayList<>();
        setList.add(mappableSet);

        User mappableUser = new User(r.getString(7));

        mappable.setExerciseList(mappableEl);
        mappable.setWorkoutName(r.getString(3));
        mappable.setDescription(r.getString(4));
        // TODO: 27/12/2017 map category
        mappable.setSets(setList);
        mappable.setUser(mappableUser);
        mappable.setPublic(r.getBoolean(8));

        if (r.getDate(9) != null){
            mappable.setDate(new DateTime(r.getDate(9)).plusDays(1));
        }


        return mappable;
    }
}
