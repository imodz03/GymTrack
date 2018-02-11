package Entity.mappers;

import Entity.Log;
import Entity.Set;
import Entity.User;
import Entity.Workout;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LogMapper implements ResultSetMapper<Log> {

    @Override
    public Log map(int index, ResultSet r, StatementContext ctx) throws SQLException {

        Log mappable = new Log(r.getString(1));

        Set mappableSet = new Set(r.getString(2));

        List<Set> temp = new ArrayList();

        temp.add(mappableSet);

        Workout mappableWorkout = new Workout(r.getString(3));

        User mappableUser = new User(r.getString(4));

        mappable.setSets(temp);
        mappable.setWorkout(mappableWorkout);
        mappable.setUser(mappableUser);

        return mappable;
    }
}
