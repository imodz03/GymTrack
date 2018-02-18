import Configuration.ApplicationConfig;
import Configuration.GymTrackModule;
import Auth.AuthDynamicFeature;
import com.hubspot.dropwizard.guice.GuiceBundle;
import io.dropwizard.Application;
import io.dropwizard.jersey.setup.JerseyEnvironment;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.util.stream.Stream;


public class GymTrack extends Application<ApplicationConfig> {

    private GuiceBundle<ApplicationConfig> guiceBundle;
    private GymTrackModule myModule;

    public static void main(String[] args) throws Exception {
        new GymTrack().run(args);
    }

    @Override
    public void initialize(Bootstrap<ApplicationConfig> bootstrap) {
        myModule = new GymTrackModule();

        guiceBundle = GuiceBundle.<ApplicationConfig>newBuilder()
                .addModule(myModule)
                .enableAutoConfig("Resources")
                .setConfigClass(ApplicationConfig.class).build();

        bootstrap.addBundle(guiceBundle);
    }

    @Override
    public void run(ApplicationConfig configuration, Environment environment) throws Exception {

        myModule.setupModule(environment, configuration);

        JerseyEnvironment je = environment.jersey();

        Stream.of(new AuthDynamicFeature()).forEach(je::register);


    }


}
