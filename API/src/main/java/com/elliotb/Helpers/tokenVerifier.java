package com.elliotb.Helpers;
import com.elliotb.Auth.Beans.AuthUser;

public interface tokenVerifier {

    boolean verify(AuthUser au);

}
