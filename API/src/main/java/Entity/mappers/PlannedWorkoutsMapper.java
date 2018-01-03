package Entity.mappers;

import Entity.PlannedWorkouts;
import Entity.Workout;
import Entity.enums.DoW;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlannedWorkoutsMapper implements ResultSetMapper<PlannedWorkouts> {

    @Override
    public PlannedWorkouts map(int index, ResultSet r, StatementContext ctx) throws SQLException {

        PlannedWorkouts mappable = new PlannedWorkouts(r.getString(1));
        Workout mappableWorkout = new Workout();

        mappable.setPlanID(r.getString(2));

        mappableWorkout.setWorkoutID(r.getString(3));

        mappable.setWorkout(mappableWorkout);

        mappable.setWorkoutDay(r.getInt(4));

        if (r.getString(5) != null){
            mappable.setDayOfWeek(DoW.valOf(r.getString(5)));
        }else{
            mappable.setDayOfWeek(DoW.NONE);
        }

        return mappable;
    }
}
