import Configuration.ApplicationConfig;
import Configuration.GymTrackModule;
import Resources.Auth.AuthDynamicFeature;
import com.auth0.jwt.algorithms.Algorithm;
import com.google.inject.Inject;
import com.hubspot.dropwizard.guice.GuiceBundle;
import io.dropwizard.Application;
import io.dropwizard.jersey.setup.JerseyEnvironment;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;


public class GymTrack extends Application<ApplicationConfig> {

    private GuiceBundle<ApplicationConfig> guiceBundle;
    private GymTrackModule myModule;

    @Inject
    private Algorithm algorithm;

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

        AuthDynamicFeature authDynamicFeature = new AuthDynamicFeature();
        je.register(authDynamicFeature);

    }


}
