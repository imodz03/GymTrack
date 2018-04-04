package com.elliotb.Entity;

import org.joda.time.DateTime;

import java.util.List;

public class Goal {

    private String goalID;
    private String goalName;
    private List<Set> set;
    private User user;
    private DateTime targetDate;
    private DateTime dateAchieved;

    public Goal(){}

    public Goal(String goalID){
        this();
        this.goalID = goalID;
    }

    public Goal(String goalID, List<Set> sets, User user, DateTime targetDate, DateTime dateAchieved) {
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

    public List<Set> getSet() {
        return set;
    }

    public void setSet(List<Set> set) {
        this.set = set;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTargetDate() {
        if (targetDate != null){
            return targetDate.toString("yyyy-MM-dd");
        }else{
            return null;
        }
    }

    public void setTargetDate(DateTime targetDate) {
        this.targetDate = targetDate;
    }

    public String getDateAchieved() {
        if (dateAchieved != null){
            return dateAchieved.toString("yyyy-MM-dd");
        }else{
            return null;
        }
    }

    public void setDateAchieved(DateTime dateAchieved) {
        this.dateAchieved = dateAchieved;
    }

    public String getGoalName() {
        return goalName;
    }

    public void setGoalName(String goalName) {
        this.goalName = goalName;
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
