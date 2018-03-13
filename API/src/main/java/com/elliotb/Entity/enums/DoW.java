package com.elliotb.Entity.enums;

public enum DoW {

    MONDAY("Mon", 1),
    TUESDAY("Tue", 2),
    WEDNESDAY("Wed", 3),
    THURSDAY("Thu", 4),
    FRIDAY("Fri", 5),
    SATURDAY("Sat", 6),
    SUNDAY("Sun", 7),
    NONE("None", 0);

    String val;
    int num;

    DoW(String val, int num){
        this.val = val;
        this.num = num;
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

    public int getNum() {
        return num;
    }
}
