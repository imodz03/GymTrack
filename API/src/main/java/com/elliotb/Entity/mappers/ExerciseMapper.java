package com.elliotb.Entity.mappers;

import com.elliotb.Entity.Exercise;
import com.elliotb.Entity.enums.Bodypart;
import com.elliotb.Entity.enums.ExerciseCategory;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExerciseMapper implements ResultSetMapper<Exercise> {

    @Override
    public Exercise map(int index, ResultSet r, StatementContext ctx) throws SQLException {

        Exercise mapped = new Exercise();
        mapped.setExerciseID(r.getString(1));
        mapped.setExerciseName(r.getString(2));
        mapped.setDescription(r.getString(3));

        ExerciseCategory excatM = ExerciseCategory.DEFAULT;

        if (r.getString(4).contains("Strength")){
            mapped.setExerciseCategory(ExerciseCategory.STRENGTH_TRAINING);
        }else{
            mapped.setExerciseCategory(ExerciseCategory.valueOf(r.getString(4).toUpperCase()));
        }

        mapped.setBodypart(Bodypart.valueOf(r.getString(5).toUpperCase()));

        return mapped;
    }
}