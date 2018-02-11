package Configuration;

import DAO.*;
import Services.ExerciseListService;
import Services.ExerciseService;
import Services.Implementation.ExerciseListServiceImpl;
import Services.Implementation.ExerciseServiceImpl;
import Services.Implementation.WorkoutServiceImpl;
import Services.WorkoutService;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;

public class GymTrackModule extends AbstractModule{

    private DBI jdbi;

    public void setupModule(Environment env, ApplicationConfig config){

        final DBIFactory factory = new DBIFactory();
        this.jdbi = factory.build(env, config.getDataSourceFactory(), "mysql");

    }

    @Override
    protected void configure() {
        bind(ExerciseService.class).to(ExerciseServiceImpl.class);
        bind(ExerciseListService.class).to(ExerciseListServiceImpl.class);
        bind(WorkoutService.class).to(WorkoutServiceImpl.class);
    }

    @Provides
    ExerciseDAO provideExerciseDAO(){
        return jdbi.onDemand(ExerciseDAO.class);
    }

    @Provides
    UserDAO provideUserDAO(){
        return jdbi.onDemand(UserDAO.class);
    }

    @Provides
    ExerciseListDAO provideExerciseListDAO(){
        return jdbi.onDemand(ExerciseListDAO.class);
    }

    @Provides
    SetDAO provideSetDAO(){
        return jdbi.onDemand(SetDAO.class);
    }

    @Provides
    GoalDAO providesGoalDAO(){
        return jdbi.onDemand(GoalDAO.class);
    }

    @Provides
    WorkoutDAO providesWorkoutDAO(){
        return jdbi.onDemand(WorkoutDAO.class);
    }

    @Provides
    PlanDAO providesPlanDAO(){
        return jdbi.onDemand(PlanDAO.class);
    }

    @Provides
    PlannedWorkoutsDAO providesPlannedWorkoutsDAO(){
        return jdbi.onDemand(PlannedWorkoutsDAO.class);
    }

    @Provides
    LogDAO providesLogDAO(){
        return jdbi.onDemand(LogDAO.class);
    }


}
