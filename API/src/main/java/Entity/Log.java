package Entity;

import java.util.List;

public class Log {

    private String logID;
    private List<Set> sets;
    private Workout workout;
    private User user;

    public Log(){}

    public Log(String logID) {
        this.logID = logID;
    }

    public String getLogID() {
        return logID;
    }

    public void setLogID(String logID) {
        this.logID = logID;
    }

    public List<Set> getSets() {
        return sets;
    }

    public void setSets(List<Set> sets) {
        this.sets = sets;
    }

    public Workout getWorkout() {
        return workout;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSetID(){
        String ret = "";
        if (sets.size() > 0 ){
            ret = sets.get(0).getSetID();
        }

        return  ret;
    }
}
