package com.elliotb.Entity.enums;

public enum DoW {

    MONDAY("Mon"),
    TUESDAY("Tue"),
    WEDNESDAY("Wed"),
    THURSDAY("Thu"),
    FRIDAY("Fri"),
    SATURDAY("Sat"),
    SUNDAY("Sun"),
    NONE("None");

    String val;

    DoW(String val){
        this.val = val;
    }

    public static DoW valOf(String val){
        for (DoW doW : DoW.values()) {
            if (doW.val.equals(val)){
                return doW;
            }
        }

        return null;

    }

    public String getVal(){
        return val;
    }
}
