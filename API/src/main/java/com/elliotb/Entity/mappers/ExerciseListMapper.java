package com.elliotb.Entity.mappers;

import com.elliotb.Entity.Exercise;
import com.elliotb.Entity.ExerciseList;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExerciseListMapper implements ResultSetMapper<ExerciseList> {

    @Override
    public ExerciseList map(int index, ResultSet r, StatementContext ctx) throws SQLException {

        ExerciseList el = new ExerciseList(r.getString(1));

        Exercise exercise = new Exercise(r.getString(2));

        el.getExercises().add(exercise);

        return el;
    }
}
