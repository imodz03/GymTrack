package Entity;

import org.joda.time.DateTime;

public class Goal {

    private String goalID;
    private Set set;
    private User user;
    private DateTime targetDate;
    private DateTime dateAchieved;

    public Goal(){}

    public Goal(String goalID){
        this();
        this.goalID = goalID;
    }

    public Goal(String goalID, Set sets, User user, DateTime targetDate, DateTime dateAchieved) {
        this();
        this.goalID = goalID;
        this.set = sets;
        this.user = user;
        this.targetDate = targetDate;
        this.dateAchieved = dateAchieved;
    }

    public String getGoalID() {
        return goalID;
    }

    public void setGoalID(String goalID) {
        this.goalID = goalID;
    }

    public Set getSet() {
        return set;
    }

    public void setSet(Set set) {
        this.set = set;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTargetDate() {
        return targetDate.toString("yyyy-MM-dd");
    }

    public void setTargetDate(DateTime targetDate) {
        this.targetDate = targetDate;
    }

    public String getDateAchieved() {
        return dateAchieved.toString("yyyy-MM-dd");
    }

    public void setDateAchieved(DateTime dateAchieved) {
        this.dateAchieved = dateAchieved;
    }

    @Override
    public String toString() {
        return "Goal{" +
                "goalID='" + goalID + '\'' +
                ", set=" + set +
                ", user=" + user +
                ", targetDate=" + targetDate +
                ", dateAchieved=" + dateAchieved +
                '}';
    }
}
