package com.elliotb.Auth.Beans;

public enum ROLE {

    MEMBER(0), MODERATOR(1), ADMIN(2);

    public int val;

    ROLE(int val){
        this.val = val;
    }

    public static ROLE getRoleOfVal(int val){

        for (ROLE role : ROLE.values()) {
            if (role.val == val){
                return role;
            }else{
                return null;
            }
        }
        return null;
    }

}
