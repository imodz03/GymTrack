package Helpers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import javax.inject.Inject;
import javax.ws.rs.core.HttpHeaders;
import java.util.Map;

public class tokenDecrypterImpl implements tokenDecrypter {

    @Inject
    private Algorithm algorithm;

    @Override
    public String getId(HttpHeaders httpHeaders) {

        String token = httpHeaders.getHeaderString("userToken");

        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("ElliotB").build();

        try{

            DecodedJWT decodedJWT = verifier.verify(token);

            Map<String, Claim> claims = decodedJWT.getClaims();

            return claims.get("userID").asString();

        }catch (JWTDecodeException e){
            return null;
        }catch (Exception e){
            return null;
        }
    }
}
