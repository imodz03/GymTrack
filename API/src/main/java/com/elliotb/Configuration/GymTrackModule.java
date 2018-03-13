package com.elliotb.Configuration;

import com.elliotb.DAO.*;
import com.elliotb.Helpers.*;
import com.elliotb.Services.ExerciseListService;
import com.elliotb.Services.ExerciseService;
import com.elliotb.Services.Implementation.ExerciseListServiceImpl;
import com.elliotb.Services.Implementation.ExerciseServiceImpl;
import com.elliotb.Services.Implementation.PlanServiceImpl;
import com.elliotb.Services.Implementation.WorkoutServiceImpl;
import com.elliotb.Services.PlanService;
import com.elliotb.Services.WorkoutService;
import com.auth0.jwt.algorithms.Algorithm;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class GymTrackModule extends AbstractModule{

    private DBI jdbi;
    private ApplicationConfig config;

    public void setupModule(Environment env, ApplicationConfig config){

        final DBIFactory factory = new DBIFactory();
        this.jdbi = factory.build(env, config.getDataSourceFactory(), "mysql");
        this.config = config;

    }

    @Provides
    public Algorithm getAlgorithm(){
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

    @Override
    protected void configure() {
        bind(ExerciseService.class).to(ExerciseServiceImpl.class);
        bind(ExerciseListService.class).to(ExerciseListServiceImpl.class);
        bind(WorkoutService.class).to(WorkoutServiceImpl.class);
        bind(tokenVerifier.class).to(tokenVerifierImpl.class);
        bind(tokenDecrypter.class).to(tokenDecrypterImpl.class);
        bind(PlanService.class).to(PlanServiceImpl.class);
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

    @Provides
    AuthDAO providesAuthDAO(){
        return jdbi.onDemand(AuthDAO.class);
    }


}
