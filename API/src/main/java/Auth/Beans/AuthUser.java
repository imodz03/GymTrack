package Auth.Beans;

import com.auth0.jwt.interfaces.Claim;

import java.util.Map;

public class AuthUser {

    private String username;
    private String userID;
    private String pass;
    private ROLE role = ROLE.MEMBER;
    private String token;

    public AuthUser(){}

    public AuthUser(String username, String userID) {
        this.username = username;
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ROLE getRole() {
        return role;
    }

    public void setRole(ROLE role) {
        this.role = role;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public static AuthUser build(Map<String, Claim> map){

        AuthUser au = new AuthUser(map.get("username").asString(), map.get("userID").asString());
        au.setRole(ROLE.getRoleOfVal(map.get("role").asInt()));

        return au;
    }
}
