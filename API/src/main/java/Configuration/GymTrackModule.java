package Configuration;

import DAO.ExerciseDAO;
import DAO.UserDAO;
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

    }

    @Provides
    ExerciseDAO provideExerciseDAO(){
        return jdbi.onDemand(ExerciseDAO.class);
    }

    @Provides
    UserDAO provideUserDAO(){
        return jdbi.onDemand(UserDAO.class);
    }


}
