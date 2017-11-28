import Configuration.ApplicationConfig;
import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;

public class GymTrack extends Application<ApplicationConfig> {

    public static void main(String[] args) throws Exception {
        new GymTrack().run(args);
    }

    @Override
    public void run(ApplicationConfig configuration, Environment environment) throws Exception {

        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "mysql");


    }

}
