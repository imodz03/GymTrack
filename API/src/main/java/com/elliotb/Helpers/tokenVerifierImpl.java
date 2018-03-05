package com.elliotb.Helpers;

import com.elliotb.Auth.Beans.AuthUser;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import javax.inject.Inject;

public class tokenVerifierImpl implements tokenVerifier {

    @Inject
    private Algorithm algorithm;

    public boolean verify(AuthUser user){

        JWTVerifier verifier = JWT.require(algorithm).withIssuer("ElliotB").build();

        try{

            DecodedJWT decodedJWT = verifier.verify(user.getToken());

            return true;

        }catch (SignatureVerificationException error){
            return false;
        }catch (Exception ex){
            return false;
        }

    }

}
