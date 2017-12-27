package Entity.mappers;

import Entity.Workout;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkoutMapper implements ResultSetMapper<Workout> {

    @Override
    public Workout map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return null;
    }
}
