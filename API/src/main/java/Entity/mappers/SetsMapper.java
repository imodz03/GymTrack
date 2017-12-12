package Entity.mappers;

import Entity.Exercise;
import Entity.Set;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SetsMapper implements ResultSetMapper<Set> {

    @Override
    public Set map(int index, ResultSet r, StatementContext ctx) throws SQLException {

        Set mappable = new Set();

        mappable.setSetID(r.getString(1));
        mappable.setExercise(new Exercise(r.getString(2)));
        mappable.setPosition(r.getInt(3));
        mappable.setReps(r.getInt(4));
        mappable.setWeight(r.getDouble(5));
        mappable.setTime(r.getDouble(6));
        mappable.setDistance(r.getDouble(7));

        //System.out.println(mappable.toString());

        return mappable;
    }
}
