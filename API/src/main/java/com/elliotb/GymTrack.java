package com.elliotb;

import com.elliotb.Auth.AuthDynamicFeature;
import com.elliotb.Configuration.ApplicationConfig;
import com.elliotb.Configuration.GymTrackModule;
import com.hubspot.dropwizard.guice.GuiceBundle;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.jersey.setup.JerseyEnvironment;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;
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
                .enableAutoConfig("com/elliotb/Resources")
                .setConfigClass(ApplicationConfig.class).build();

        bootstrap.addBundle(guiceBundle);

        bootstrap.addBundle(new AssetsBundle("/resources", "/resources"));

    }

    @Override
    public void run(ApplicationConfig configuration, Environment environment) throws Exception {

        final FilterRegistration.Dynamic cors = environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin, userToken");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

        // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

        myModule.setupModule(environment, configuration);

        JerseyEnvironment je = environment.jersey();

        Stream.of(new AuthDynamicFeature()).forEach(je::register);


    }


}
