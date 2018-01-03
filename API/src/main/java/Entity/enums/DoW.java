package Entity.enums;

public enum DoW {

    MONDAY("Mon"),
    TUESDAY("Tue"),
    WEDNESDAY("Wed"),
    THURSDAY("Thu"),
    FRIDAY("Fri"),
    SATURDAY("Sat"),
    SUNDAY("Sun");

    String val;

    DoW(String val){
        this.val = val;
    }

    public static DoW valOf(String val){
        System.out.println(val);
        for (DoW doW : DoW.values()) {
            System.out.println(doW.val);
            if (doW.val.equals(val)){
                return doW;
            }
        }

        return null;

    }

    @Override
    public String toString() {
        return val;
    }
}
