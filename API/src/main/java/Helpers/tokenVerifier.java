package Helpers;
import Auth.Beans.AuthUser;

public interface tokenVerifier {

    boolean verify(AuthUser au);

}
