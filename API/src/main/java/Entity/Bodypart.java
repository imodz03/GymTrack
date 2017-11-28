package Entity;

public enum Bodypart {
    ARMS("Arms"),
    LEGS("Legs"),
    BACK("Back"),
    CHEST("Chest"),
    SHOULDERS("Shoulders");

    private String val;

    Bodypart(String value){
        this.val = value;
    }
}
