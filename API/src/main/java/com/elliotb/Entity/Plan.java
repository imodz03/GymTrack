package com.elliotb.Entity;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class Plan {

    private String planID;
    private String planName;
    private String description;
    private User user;
    private boolean isPublic;
    private int repeats = 0;
    private DateTime startDate;

    private List<PlannedWorkouts> workouts;

    public Plan(){
        workouts = new ArrayList<>();
    }

    public Plan(String planID, String planName) {
        this();
        this.planID = planID;
        this.planName = planName;
    }

    public String getPlanID() {
        return planID;
    }

    public void setPlanID(String planID) {
        this.planID = planID;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public int getRepeats() {
        return repeats;
    }

    public void setRepeats(int repeats) {
        this.repeats = repeats;
    }

    public String getStartDate() {
        return startDate.toString("yyyy-MM-dd");
    }

    public void setStartDate(DateTime startDate) {
        this.startDate = startDate;
    }
}
