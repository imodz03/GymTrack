import Configuration.ApplicationConfig;
import Configuration.GymTrackModule;
import Helpers.Keystore;
import Resources.Auth.AuthDynamicFeature;
import com.auth0.jwt.algorithms.Algorithm;
import com.hubspot.dropwizard.guice.GuiceBundle;
import io.dropwizard.Application;
import io.dropwizard.jersey.setup.JerseyEnvironment;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

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

        AuthDynamicFeature authDynamicFeature = new AuthDynamicFeature(getAlgorithm(configuration));
        je.register(authDynamicFeature);

    }

    private Algorithm getAlgorithm(ApplicationConfig config){
        try{
            KeyPair kp = Keystore.getKeyPairFromKeyStore(config);

            RSAPublicKey publicKey = (RSAPublicKey)kp.getPublic();
            RSAPrivateKey privateKey = (RSAPrivateKey)kp.getPrivate();

            Algorithm algorithm = Algorithm.RSA256(publicKey, privateKey);

            return algorithm;

        }catch (Exception ex){
            System.out.println("Error loading keystore");
            return null;
        }

    }


}
