package Resources.Auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.core.Response;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Stream;

public class AuthDynamicFeature implements DynamicFeature {

    @Inject
    private Algorithm algorithm;

    public AuthDynamicFeature(){


    }

    @Override
    public void configure(ResourceInfo resourceInfo, FeatureContext context) {

        final Method resourceMethod = resourceInfo.getResourceMethod();
        if (resourceMethod != null){

            Stream.of(resourceMethod.getParameterAnnotations())
                    .flatMap(Arrays::stream)
                    .filter(annotation -> annotation.annotationType().equals(AuthRequired.class))
                    .map(AuthRequired.class::cast)
                    .findFirst()
                    .ifPresent(authRequired -> context.register(getAuthFilter(authRequired)));

            AuthRequired ar = resourceMethod.getAnnotation(AuthRequired.class);

            if (ar != null){
                context.register(getAuthFilter(ar));
            }


        }

    }

    private ContainerRequestFilter getAuthFilter(AuthRequired auth){
        return requestContext -> {

            String userToken = requestContext.getHeaderString("userToken");

            JWTVerifier verifier = JWT.require(algorithm).withIssuer("ElliotB").build();

            try{
                DecodedJWT jwt = verifier.verify(userToken);
                requestContext.setProperty("user", "");
            }catch (SignatureVerificationException e){
                throw new WebApplicationException(Response.Status.FORBIDDEN);
            }
        };
    }
}
