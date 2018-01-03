package Entity.mappers;

import Entity.PlannedWorkouts;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlannedWorkoutsMapper implements ResultSetMapper<PlannedWorkouts> {

    @Override
    public PlannedWorkouts map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return null;
    }
}
