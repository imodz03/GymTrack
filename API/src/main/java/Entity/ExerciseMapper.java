package Entity;

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

        return mapped;
    }
}